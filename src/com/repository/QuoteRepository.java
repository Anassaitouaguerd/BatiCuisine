package com.repository;

import com.config.DatabaseConnection;
import com.model.Quote;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
