package com.selman.api.utilities;

import java.sql.*;

public class DatabaseManager {

    // Connection credentials (Local Shadow DB)
    private static final String URL = ConfigReader.getProperty("db_url");
    private static final String USERNAME = ConfigReader.getProperty("db_username");
    private static final String PASSWORD = ConfigReader.getProperty("db_password");

    private static Connection connection;

    // Establishes connection to the database.
    public static void connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ Database Connection Established!");

            // Create table if not exists (For simulation purposes)
            createDummyTable();

        } catch (SQLException e) {
            System.err.println("❌ Failed to connect to DB: " + e.getMessage());
        }
    }

    // Creates a dummy users table to simulate the backend DB environment.
    private static void createDummyTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "email VARCHAR(50), " +
                "name VARCHAR(50))";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Simulates backend behavior: Inserts data into local DB.
    // (In real life, the Application Backend does this, not the test code!)
    public static void insertMockUser(String email, String name) {
        String sql = "INSERT INTO users (email, name) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, email);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            System.out.println("📝 Mock Data Inserted into DB: " + email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Validates if a user exists in the database.
    public static boolean isUserRegistered(String email) {
        String query = "SELECT count(*) FROM users WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Closes the connection.
    public static void close(){
        try{
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database Connection Closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
