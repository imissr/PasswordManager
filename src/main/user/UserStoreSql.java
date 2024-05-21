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
        String sql = "CREATE TABLE IF NOT EXISTS " + account.getUsername() +" (" +
                "password text NOT NULL," +
                "user text NOT NULL PRIMARY KEY" +
                ");";

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

    public boolean creatSqlTabel(String table)  {
        String sql = "CREATE TABLE IF NOT EXISTS " + table +" (" +
                    "id INTEGER NOT NULL PRIMARY KEY UNIQUE,"+
                    "title text NOT NULL," +
                    "username text NOT NULL," +
                    "password text NOT NULL"+
                    ");";

        try (Connection connect = this.connect();
        var stmt = connect.createStatement()){
            stmt.execute(sql);
            return true;
        }catch(SQLException e){
            System.out.println("faild to creat a sqltabel");
            return false;
        }
    }






}