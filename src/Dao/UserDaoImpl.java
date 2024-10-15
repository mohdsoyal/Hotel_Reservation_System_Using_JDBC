package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.User;
import Utility.DataBaseConnectvity;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean signup(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnectvity.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the user input for username, password, and email
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());  // Storing the plain password (Not recommended)
            stmt.setString(3, user.getEmail());

            // Execute the update and return true if successful
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Return true if signup was successful
        } catch (SQLException e) {
            // Log and rethrow the exception if necessary
            System.err.println("SQL error during signup: " + e.getMessage());
            throw e; // Rethrow to handle it in the calling method
        }
    }

    @Override
    public boolean login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DataBaseConnectvity.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the user input for username and password
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // If there is a result, the user credentials are valid
            return rs.next();  // Returns true if a match is found
        } catch (SQLException e) {
            // Log and rethrow the exception if necessary
            System.err.println("SQL error during login: " + e.getMessage());
            throw e; // Rethrow to handle it in the calling method
        }
    }
}
