import java.sql.*;
import java.util.*;

public class UpdateStudent {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";
        String password = "Nidhi@15"; // replace with your MySQL password

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter student ID to update: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline

            // Fetch existing record for reference
            String checkQuery = "SELECT * FROM students WHERE id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ No student found with ID " + id);
                con.close();
                return;
            }

            System.out.println("\nCurrent details:");
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Course: " + rs.getString("course"));

            // Ask which fields to update
            System.out.print("\nEnter new name (press Enter to keep same): ");
            String newName = sc.nextLine();
            if (newName.isEmpty()) newName = rs.getString("name");

            System.out.print("Enter new email (press Enter to keep same): ");
            String newEmail = sc.nextLine();
            if (newEmail.isEmpty()) newEmail = rs.getString("email");

            System.out.print("Enter new course (press Enter to keep same): ");
            String newCourse = sc.nextLine();
            if (newCourse.isEmpty()) newCourse = rs.getString("course");

            // Update the record
            String updateQuery = "UPDATE students SET name = ?, email = ?, course = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(updateQuery);
            ps.setString(1, newName);
            ps.setString(2, newEmail);
            ps.setString(3, newCourse);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("\n✅ Record updated successfully!");
            else
                System.out.println("\n❌ Update failed!");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
