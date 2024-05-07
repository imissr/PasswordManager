import java.sql.*;

public class UserStoreSql {
    String url = "jdbc:sqlite:E:\\database\\test.db\\";

    UserStoreSql() {

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


    public void insert(Account account) {
        String insertSQL = "INSERT INTO Account(password,username) VALUES(?,?)";
        try (Connection connect = this.connect();

             PreparedStatement pstmt = connect.prepareStatement(insertSQL)) {
            pstmt.setString(1, account.getPassword());
            pstmt.setString(2, account.getUsername());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIfUsernameExists(String username) {
        String query = "SELECT COUNT(*) AS count FROM Account WHERE username = ?";
        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return false;

    }

    public String getPasswordForUsername(String username) {
        String query = "SELECT password FROM Account WHERE username = ?";
        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String password = rs.getString("password");
                    return password;

                }
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return null;
    }
}