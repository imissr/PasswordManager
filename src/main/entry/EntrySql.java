import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

public class EntrySql {

    private String url = "jdbc:sqlite:E:\\database\\test.db\\";
    private int id = 1;

     public Connection connection = null;

    EntrySql() {
        this.connect();
    }



    public final void connect() {
         connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.close();
        }


    }
    //connect function with throws beccause of GUI
    //connect function return with void
    // identify from where sql expction from is problimatic
    // connect should not be inside try -> try calls ->close interface



    public void insert(String username, String password, String email, String title) throws SQLException {
        String insertSQL = "INSERT INTO " + username + "(id,title,username,password) VALUES(?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, title);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.executeUpdate();

        }
        id++;
    }

    public void delete(String username, int id) throws SQLException {
        String deleteQUERY = "DELETE FROM " + username + " WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(deleteQUERY)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("entry deleted seccessfully");


            } else {
                System.out.println("No entry found with the specified ID.");

            }

        }
    }


    public void updateId(String username) throws SQLException {
        String fetchID = "SELECT id FROM " + username + " ORDER BY id";

        try (PreparedStatement fetch = connection.prepareStatement(fetchID);
             ResultSet rs = fetch.executeQuery()) {
            int newID = 1;
            while (rs.next()) {
                int currentID = rs.getInt("id");


                String updateSqlID = "UPDATE " + username + " SET id = ? WHERE id = ?";
                try (PreparedStatement update = connection.prepareStatement(updateSqlID)) {
                    update.setInt(1, newID);
                    update.setInt(2, currentID);
                    update.executeUpdate();
                }
                newID++;

            }

            if (checkIfTableIsClear(username)) id = 1;
            else id = newID;

            System.out.println(" id updated seccessfully");

        }
    }

    public boolean checkIfTableIsClear(String username) throws SQLException {
        String query = "SELECT COUNT(*) AS rowcount FROM " + username;

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int count = rs.getInt("rowcount");
                return count == 0;
            }
        }


        return false;
    }


    public void returnAllEntry(String username) throws SQLException {
        String query = "SELECT id,title FROM " + username;

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rst = pstmt.executeQuery()) {
            System.out.printf("%-10s %-30s%n", "ID", "Title");
            while (rst.next()) {
                int id = rst.getInt("id");
                String title = rst.getString("title");
                System.out.printf("%-10d %-30s%n", id, title);
                System.out.println("");


            }
        }
    }

    public void returnPasswordAndUsername(String username,int id) throws SQLException {
        String query = "SELECT username,password FROM " + username + " WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String password = rs.getString("password");
                    String email = rs.getString("username");
                    System.out.printf("Password: %s%nEmail: %s%n", password, email);
                } else {
                    System.out.println("No entry found with the specified ID.");
                }
            }
        }
    }

    public void deletAccount(String username) throws SQLException{
        String query = "DELETE FROM Account WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1,username);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("entry deleted seccessfully");
                deleteTable(username);
            } else {
                System.out.println("No entry found with the specified ID.");

            }
        }
    }

    public void deleteTable(String tableName) throws SQLException {
        // Ensure the table name is safe to use in SQL queries
        if (tableName == null || !tableName.matches("[a-zA-Z0-9_]+")) {
            throw new IllegalArgumentException("Invalid table name: " + tableName);
        }

        String query = "DROP TABLE " + tableName;
        try (
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Table " + tableName + " deleted successfully");
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
            // Handle the exception properly, potentially rethrowing or logging it
        }
    }



    public void close()  {
        if(connection == null)
            return;
        try {
            connection.close();
            connection = null;
        }catch (SQLException e ){
            System.err.println(e.getMessage());
        }
    }
}
