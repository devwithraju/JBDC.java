import java.sql.*;
import java.util.*;

public class InsertStudent {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root"; // your MySQL username
        String password = "Nidhi@15"; // replace with your MySQL password

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Scanner sc = new Scanner(System.in);

            String query = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            System.out.print("How many students do you want to insert? ");
            int n = sc.nextInt();
            sc.nextLine(); // consume newline

            for (int i = 1; i <= n; i++) {
                System.out.println("\nEnter details for Student " + i + ":");
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Course: ");
                String course = sc.nextLine();

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, course);
                ps.executeUpdate();
            }

            System.out.println("\n✅ Students inserted successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
