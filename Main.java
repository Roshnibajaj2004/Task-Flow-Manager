package taskflow;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        TaskService taskService = new TaskService();

        System.out.println("=== Task Flow Manager ===");

        boolean running = true;
        int userId = -1;

        while (running) {
            if (userId == -1) {
                System.out.println("1. Register\n2. Login\n3. Exit");
                int choice = InputHelper.getInt("Enter choice: ");

                switch (choice) {
                    case 1 -> userService.register();
                    case 2 -> userId = userService.login();
                    case 3 -> running = false;
                    default -> System.out.println("Invalid choice");
                }
            } else {
                System.out.println("1. Add Task\n2. View Tasks\n3. Update Task\n4. Delete Task\n5. Logout");
                int choice = InputHelper.getInt("Enter choice: ");

                switch (choice) {
                    case 1 -> taskService.addTask(userId);
                    case 2 -> taskService.viewTasks(userId);
                    case 3 -> taskService.updateTask(userId);
                    case 4 -> taskService.deleteTask(userId);
                    case 5 -> userId = -1;
                    default -> System.out.println("Invalid choice");
                }
            }
        }
    }
}
