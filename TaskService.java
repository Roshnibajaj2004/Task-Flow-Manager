package taskflow;

import java.sql.*;

public class TaskService {
    public void addTask(int userId) {
        String title = InputHelper.getString("Enter title: ");
        String desc = InputHelper.getString("Enter description: ");
        String status = InputHelper.getString("Enter status (TODO/IN_PROGRESS/DONE): ");
        String deadlineStr = InputHelper.getString("Enter deadline (YYYY-MM-DD): ");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO tasks (user_id, title, description, status, deadline) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, userId);
            stmt.setString(2, title);
            stmt.setString(3, desc);
            stmt.setString(4, status);
            stmt.setDate(5, Date.valueOf(deadlineStr));
            stmt.executeUpdate();
            System.out.println("Task added.");
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    public void viewTasks(int userId) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tasks WHERE user_id = ?");
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.printf("[%d] %s - %s (%s) Deadline: %s\n",
                        rs.getInt("id"), rs.getString("title"), rs.getString("description"),
                        rs.getString("status"), rs.getDate("deadline"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
    }

    public void updateTask(int userId) {
        int id = InputHelper.getInt("Enter task ID to update: ");
        String status = InputHelper.getString("Enter new status (TODO/IN_PROGRESS/DONE): ");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE tasks SET status = ? WHERE id = ? AND user_id = ?"
            );
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.setInt(3, userId);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Task updated.");
            else System.out.println("Task not found or unauthorized.");
        } catch (SQLException e) {
            System.out.println("Error updating task: " + e.getMessage());
        }
    }

    public void deleteTask(int userId) {
        int id = InputHelper.getInt("Enter task ID to delete: ");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tasks WHERE id = ? AND user_id = ?");
            stmt.setInt(1, id);
            stmt.setInt(2, userId);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Task deleted.");
            else System.out.println("Task not found or unauthorized.");
        } catch (SQLException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }
}
