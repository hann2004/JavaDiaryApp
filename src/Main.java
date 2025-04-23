import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static String url = "jdbc:postgresql://localhost:5432/Personal_diary";
    static String user = "postgres";
    static String password = "55550000";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

            while (true) {
                try {
                    System.out.println("\nDiary App Menu:");
                    System.out.println("1. Add new diary entry");
                    System.out.println("2. View all diary entries");
                    System.out.println("3. View a single diary entry");
                    System.out.println("4. Delete a diary entry");
                    System.out.println("5. Exit");
                    System.out.print("Choose an option: ");

                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            addNewEntry(scanner, conn);
                            break;
                        case 2:
                            viewAllEntries(conn);
                            break;
                        case 3:
                            viewSingleEntry(scanner, conn);
                            break;
                        case 4:
                            deleteEntry(scanner, conn);
                            break;
                        case 5:
                            System.out.println(" Exiting the Diary App. Goodbye!");
                            return;
                        default:
                            System.out.println(" Invalid option. Please try again.");
                    }

                    // After each operation
                    System.out.print("\n Press # to return to Main Menu or * to Exit: ");
                    String next = scanner.nextLine();
                    if (next.equals("*")) {
                        System.out.println(" Exiting the Diary App. Goodbye!");
                        break;
                    } else if (!next.equals("#")) {
                        System.out.println(" Invalid input. Returning to Main Menu...");
                    }

                } catch (NumberFormatException e) {
                    System.out.println(" Please enter a number only.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addNewEntry(Scanner scanner, Connection conn) {
        System.out.print("Enter the title of the diary entry: ");
        String title = scanner.nextLine();
        System.out.print("Enter the content of the diary entry: ");
        String content = scanner.nextLine();

        String sql = "INSERT INTO diary_entries (title, content) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, content);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(" Diary entry added successfully!");
            } else {
                System.out.println(" Failed to add diary entry.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAllEntries(Connection conn) {
        String sql = "SELECT * FROM diary_entries";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("\nID: " + rs.getInt("id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Date: " + rs.getTimestamp("created_at"));
                System.out.println("Content: " + rs.getString("content"));
                System.out.println("--------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewSingleEntry(Scanner scanner, Connection conn) {
        try {
            System.out.print("Enter the ID of the diary entry to view: ");
            int id = Integer.parseInt(scanner.nextLine());

            String sql = "SELECT * FROM diary_entries WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    System.out.println("\nID: " + rs.getInt("id"));
                    System.out.println("Title: " + rs.getString("title"));
                    System.out.println("Date: " + rs.getTimestamp("created_at"));
                    System.out.println("Content: " + rs.getString("content"));
                } else {
                    System.out.println(" Entry not found with ID: " + id);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(" Please enter a valid number for the ID.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEntry(Scanner scanner, Connection conn) {
        try {
            System.out.print("Enter the ID of the diary entry to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            String sql = "DELETE FROM diary_entries WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println(" Diary entry deleted successfully!");
                } else {
                    System.out.println(" No entry found with ID: " + id);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(" Please enter a valid number for the ID.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
