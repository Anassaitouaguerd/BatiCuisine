    package com.repository;
    
    import com.config.DatabaseConnection;
    import com.model.Labor;
    import com.model.Material;
    import com.model.Project;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.util.*;

    public class ComponentRepository {
        public Long createComponentMaterial(Material material) {
            String querySQL = "INSERT INTO component (name, unitcost, quantity, componenttype, projectid) VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(querySQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, material.getName());
                statement.setDouble(2, material.getUnitCost());
                statement.setDouble(3, material.getQuantity());
                statement.setString(4, material.getComponentType().name());
                statement.setLong(5, material.getProject().getId());
                int insertedRow = statement.executeUpdate();
                if (insertedRow > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            System.out.println("this is id of last component inserted " + generatedKeys.getLong(1));
                            return generatedKeys.getLong(1);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0L;
        }
        public Long createComponentLabor(Labor labor) {
            String querySQL = "INSERT INTO component (name, componenttype, projectid) VALUES (?, ?, ?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(querySQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, labor.getName());
                statement.setString(2, labor.getComponentType().name());
                statement.setLong(3, labor.getProject().getId());
                int RowInserted = statement.executeUpdate();
                if (RowInserted > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            System.out.println("this is id of last component inserted " + generatedKeys.getLong(1));
                            return generatedKeys.getLong(1);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0L;
        }
        public void updateComponent(Double vat, Long projectId) {
            String querySQL = "UPDATE component SET vatrate = ? WHERE projectid = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(querySQL)) {
                statement.setDouble(1, vat);
                statement.setLong(2, projectId);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("An existing component was updated successfully with id: " + projectId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public List<Object> getComponent(Long lastProjectId) {
            List<Object> components = new ArrayList<>();
            String querySQL = "SELECT c.id AS component_id, c.name AS component_name, c.componenttype, c.unitcost AS material_unitcost, " +
                    "c.quantity AS material_quantity, c.vatrate, c.projectid, " +
                    "m.transportcost AS material_transportCost, " +
                    "m.qualitycoefficient AS material_qualityCoefficient, " +
                    "l.hourlyrate AS labor_hourlyRate, " +
                    "l.workhours AS labor_workHours, l.workerproductivity AS labor_workerProductivity, " +
                    "l.labortype AS labor_type " + // Removed trailing comma here
                    "FROM component c " +  // No quotes needed for component if it's not reserved
                    "LEFT JOIN material m ON c.id = m.id " +
                    "LEFT JOIN labor l ON c.id = l.id " +
                    "WHERE c.projectid = ?";


            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(querySQL)) {
                statement.setLong(1, lastProjectId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getString("componenttype").equals("MATERIAL")) {
                        Material materials = new Material(
                                resultSet.getLong("component_id"),
                                resultSet.getString("component_name"),
                                resultSet.getDouble("material_unitcost"),
                                resultSet.getDouble("material_quantity"),
                                resultSet.getDouble("vatrate"),
                                new Project(resultSet.getLong("projectid")),
                                resultSet.getDouble("material_transportCost"),
                                resultSet.getDouble("material_qualityCoefficient")
                                );
                        components.add(materials);
                    } else if (resultSet.getString("componenttype").equals("LABOR")) {
                        Labor labors = new Labor(
                                resultSet.getLong("component_id"),
                                resultSet.getString("component_name"),
                                resultSet.getString("labor_type"),
                                resultSet.getDouble("labor_hourlyRate"),
                                resultSet.getDouble("labor_workHours"),
                                resultSet.getDouble("labor_workerProductivity"),
                                resultSet.getDouble("vatrate"),
                                new Project(resultSet.getLong("projectid"))
                        );
                        components.add(labors);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return components; // Adjust the return type as needed
        }



    }
