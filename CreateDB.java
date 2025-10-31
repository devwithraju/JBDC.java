import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDB {
    public static void main(String[] args) {
        // Change these values as per your MySQL setup
         try {
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";           // your MySQL username
            String password = "Nidhi@15";  // your MySQL password
            String dbName = "StudentDB";    // the name of the new database

    
            // Step 1: Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection to MySQL server
            Connection con = DriverManager.getConnection(url, user, password);

            // Step 3: Create statement
            Statement stmt = con.createStatement();

            // Step 4: Execute query to create database
            String query = "CREATE DATABASE " + dbName;
            stmt.executeUpdate(query);

            System.out.println("Database '" + dbName + "' created successfully!");

            // Step 5: Close connection
            stmt.close();
            con.close();
        } 
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
