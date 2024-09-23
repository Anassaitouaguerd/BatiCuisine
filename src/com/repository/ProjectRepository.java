package com.repository;
import com.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Client;
import com.model.Project;

public class ProjectRepository {

    public long createProject(Project project) {
        String querySQL = "INSERT INTO project (projectname, surface, clientid) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(querySQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, project.getProjectName());
            statement.setDouble(2, project.getSurface());
            statement.setLong(3, project.getClient().getId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long lastProjectId = generatedKeys.getLong(1);
                    System.out.println("A new project was added successfully with name: " + project.getProjectName());
                    return lastProjectId;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateProject(Double profitMargin, Long projectId) {
        String querySQL = "UPDATE project SET profitmargin = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(querySQL)) {
            statement.setDouble(1, profitMargin);
            statement.setLong(2, projectId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing project was updated successfully with id: " + projectId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProject() {
    }

    public Project getProject(Long lastProjectId) {
        String querySQL = "SELECT * FROM project WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stetment = connection.prepareStatement(querySQL);
        ){
            stetment.setLong(1,lastProjectId);
            ResultSet resultSet = stetment.executeQuery();

            if (resultSet.next()){
                return new Project(resultSet.getLong("id"),
                        resultSet.getString("projectname"),
                        resultSet.getDouble("profitmargin"),
                        resultSet.getDouble("totalcost"),
                        Project.ProjectStatus.valueOf(resultSet.getString("projectstatus")),
                        resultSet.getDouble("surface"),
                        new Client(resultSet.getLong("clientid")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Project> getAllProjects() {
    List<Project> projects = new ArrayList<>();
    String sqlQuery = "SELECT * FROM project";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            Project project = new Project();
            project.setId(resultSet.getLong("id"));
            project.setProjectName(resultSet.getString("projectname"));
            project.setSurface(resultSet.getDouble("surface"));
            project.setClient(new Client(resultSet.getLong("clientid")));
            project.setProfitMargin(resultSet.getDouble("profitmargin"));
            project.setTotalCost(resultSet.getDouble("totalcost"));
            // TODO: GIVE THE STATUS OF ANY PROJECT DEFAULT VALUE AND HANDLE IF HE IS NULL
            project.setProjectStatus(Project.ProjectStatus.valueOf(resultSet.getString("projectstatus")));
            projects.add(project);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return projects;
    }

    public void updateTotalCostProject(Double totalCost, Long projectId) {
        String querySQL = "UPDATE project SET totalcost = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(querySQL)) {
            statement.setDouble(1, totalCost);
            statement.setLong(2, projectId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateProjectStatus(String status, Long projectId) {
        String querySQL = "UPDATE project SET projectstatus = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(querySQL)) {
            statement.setString(1, status);
            statement.setLong(2, projectId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean checkQuoteExist(Long projectId){
        String querySQL = "SELECT * FROM quote WHERE projectid = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(querySQL)) {
            statement.setLong(1, projectId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
