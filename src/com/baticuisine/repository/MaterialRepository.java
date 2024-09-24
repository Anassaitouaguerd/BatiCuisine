package com.baticuisine.repository;

import com.baticuisine.config.DatabaseConnection;
import com.baticuisine.model.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MaterialRepository {
    public void createMaterial(Material material , long lastComponentInserted){
        String querySQL = "INSERT INTO material (id ,transportcost , qualitycoefficient) VALUES (? ,? , ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stetment = connection.prepareStatement(querySQL);) {
            stetment.setLong(1 , lastComponentInserted);
            stetment.setDouble(2 , material.getTransportCost());
            stetment.setDouble(3 , material.getQualityCoefficient());
            int insertRow = stetment.executeUpdate();
            if(insertRow > 0){
                System.out.println("Material add with successfuly");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMaterial() {
    }

    public void deleteMaterial() {
    }

    public void getMaterial() {
    }

    public void getAllMaterials() {
    }
}
