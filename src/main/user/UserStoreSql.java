import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserStoreSql {
    String url = "jdbc:sqlite:E:\\database\\test.db\\";

    private Connection connection = null;

    UserStoreSql() {
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


    public void insert(Account account) {
        String insertSQL = "INSERT INTO Account(password,username) VALUES(?,?)";


        try (
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, account.getPassword());
            pstmt.setString(2, account.getUsername());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public boolean  checkIfUsernameExists1(String username){
        String query = "SELECT username FROM Account WHERE username = ?";
        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String check = rs.getString("username");
                    System.out.println(check);
                   if(check.equals("null"))
                    return false;
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return true;
    }*/



    public boolean checkIfUsernameExists(String username) {
        String query = "SELECT FROM Account WHERE username = ?";
        try (
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    if(count == username.length() && count > 0 ){
                        return true;
                    }

                }
            }


        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return false;

    }
    private String normalizeUsername(String username) {
        username = username.toLowerCase();
        username = username.trim();
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(username);
        return matcher.replaceAll(" ");
    }

    public boolean checkForDuplicateUsername(String inputUsername) {
        String normalizedInputUsername = normalizeUsername(inputUsername);

        try (
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT username FROM Account")) {

            while (rs.next()) {
                String username = rs.getString("username");
                String normalizedUsername = normalizeUsername(username);

                if (normalizedInputUsername.equals(normalizedUsername)) {
                    return true;  // Found a duplicate
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;  // No duplicates found
    }




    public String getPasswordForUsername(String username) {
        String query = "SELECT password FROM Account WHERE username = ?";
        try (
             PreparedStatement pstmt = connection.prepareStatement(query)) {
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

        try (
        var stmt = connection.createStatement()){
            stmt.execute(sql);
            return true;
        }catch(SQLException e){
            System.out.println("faild to creat a sqltabel");
            return false;
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