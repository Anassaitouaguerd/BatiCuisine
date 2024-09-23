package com.repository;

import com.config.DatabaseConnection;
import com.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

        public String createClient(Client client) {
            // TODO - implement ClientRepository.createClient
            String querySQL = "INSERT INTO client (name, address, phone, isprofessional) VALUES (?, ?, ?, ?)";
            try(Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(querySQL)){
                statement.setString(1, client.getName());
                statement.setString(2, client.getAddress());
                statement.setString(3, client.getPhone());
                statement.setBoolean(4, client.isProfessional());
                // Execute the SQL query
                int rowsInserted = statement.executeUpdate();

                // Check if the insertion was successful
                if (rowsInserted > 0) {
                    System.out.println("A new client was added successfully!");
                    return client.getName();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        public void updateClient() {
            // TODO - implement ClientRepository.updateClient
            throw new UnsupportedOperationException();
        }

        public void deleteClient() {
            // TODO - implement ClientRepository.deleteClient
            throw new UnsupportedOperationException();
        }

  public static Client getClient(String clientName) {
    String querySQL = "SELECT * FROM client WHERE name = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(querySQL)) {
        statement.setString(1, clientName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Client(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("address"),
                resultSet.getString("phone"),
                resultSet.getBoolean("isprofessional")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

        public void getAllClients() {
            // TODO - implement ClientRepository.getAllClients
            throw new UnsupportedOperationException();
        }
        public static int getClientId(String ClientName){
            String querySQL = "SELECT id FROM client WHERE name = ?";
            try(Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(querySQL)) {
                statement.setString(1, ClientName);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return 0;
        }
        public Client getClientById(Long clientId){
            String querySQL = "SELECT * FROM client WHERE id = ?";
            try(Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(querySQL)) {
                statement.setLong(1, clientId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return new Client(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("address"),
                            resultSet.getString("phone"),
                            resultSet.getBoolean("isprofessional")
                    );
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
}
