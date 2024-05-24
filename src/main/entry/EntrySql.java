import java.sql.*;

public class EntrySql {

    private String url = "jdbc:sqlite:E:\\database\\test.db\\";
    private int id = 1;

    EntrySql() {

    }

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;

    }


    public void insert(String username, String password, String email, String title) throws SQLException {
        String insertSQL = "INSERT INTO " + username + "(id,title,username,password) VALUES(?,?,?,?)";

        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(insertSQL)) {
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
        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(deleteQUERY)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("entry deleted seccessfully");


            } else {
                System.out.println("No entry found with the specified ID.");

            }

        }
    }

    public void deleteAccount(String username){

    }

    public void updateId(String username) throws SQLException {
        String fetchID = "SELECT id FROM " + username + " ORDER BY id";
        try (Connection connect = this.connect();
             PreparedStatement fetch = connect.prepareStatement(fetchID);
             ResultSet rs = fetch.executeQuery()) {
            int newID = 1;
            while (rs.next()) {
                int currentID = rs.getInt("id");


                String updateSqlID = "UPDATE " + username + " SET id = ? WHERE id = ?";
                try (PreparedStatement update = connect.prepareStatement(updateSqlID)) {
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

        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(query);
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
        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(query);
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
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
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



}
