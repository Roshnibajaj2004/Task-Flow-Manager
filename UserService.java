package taskflow;

import java.sql.*;

public class UserService {
    public void register() {
        String username = InputHelper.getString("Enter username: ");
        String password = InputHelper.getString("Enter password: ");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("User registered successfully.");
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    public int login() {
        String username = InputHelper.getString("Enter username: ");
        String password = InputHelper.getString("Enter password: ");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM users WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful.");
                return rs.getInt("id");
            } else {
                System.out.println("Invalid credentials.");
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
            return -1;
        }
    }
}
