package com.repository;

import com.config.DatabaseConnection;
import com.model.Project;
import com.model.Quote;

import java.sql.*;

public class QuoteRepository {
    public void createQuote(Quote quote , Long lastProjectId) {
        String sql = "INSERT INTO quote (issuedate, validitydate, projectid) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setDate(1, Date.valueOf(quote.getIssueDate()));
            preparedStatement.setDate(2, Date.valueOf(quote.getValidityDate()));
            preparedStatement.setLong(3, lastProjectId);
            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Quote added successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateQuote(String status, Long quoteId) {
        String sql = "UPDATE quote SET accepted = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, status.toLowerCase().trim().equalsIgnoreCase("validated"));
            preparedStatement.setLong(2, quoteId);
            int rowUpdated = preparedStatement.executeUpdate();
            if (rowUpdated > 0) {
                System.out.println("Status Quote updated successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Quote getQuote(Long projectId) {
        String sql = "SELECT * FROM quote WHERE projectid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Quote(resultSet.getLong("id"),
                        resultSet.getDate("issuedate").toLocalDate(),
                        resultSet.getDate("validitydate").toLocalDate(),
                        resultSet.getBoolean("accepted"),
                        new Project(resultSet.getLong("projectid"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
