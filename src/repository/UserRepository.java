package repository;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public boolean addUser(User user) {
        String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";

        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to add user: " + e.getMessage());
            return false;
        }
    }

    public User findByUsername(String username) {
        String query = "SELECT * FROM Users WHERE username = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getLong("UserID"), rs.getString("Username"),
                        rs.getString("Password"), rs.getString("Email"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find user: " + e.getMessage());
        }
        return null;
    }
}

