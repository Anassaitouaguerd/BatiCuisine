package com.repository;

import com.config.DatabaseConnection;
import com.model.Labor;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LaborRepository {
    public void creatLabor(Labor labor , long componentId) {
        String query = "INSERT INTO labor (id ,hourlyRate , workHours , workerProductivity , labortype) VALUES (?,?,?,?,?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query) ){
            statement.setLong(1 , componentId);
            statement.setDouble(2 , labor.getHourlyRate());
            statement.setDouble(3 , labor.getWorkHours());
            statement.setDouble(4 , labor.getWorkerProductivity());
            statement.setString(5 , labor.getType());
            int rowInserted = statement.executeUpdate();
            if(rowInserted > 0){
                System.out.println("Labor added successfully");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLabor() {
        System.out.println("LaborRepository.updateLabor");
    }

    public void deleteLabor() {
        System.out.println("LaborRepository.deleteLabor");
    }

    public void getLabor() {
        System.out.println("LaborRepository.getLabor");
    }

    public void getAllLabors() {
        System.out.println("LaborRepository.getAllLabors");
    }
}
