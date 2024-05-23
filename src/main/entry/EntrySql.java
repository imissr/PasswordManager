import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntrySql {

    String url = "jdbc:sqlite:E:\\database\\test.db\\";

    EntrySql(){

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



    public void insert(String username,String password,String email, int id,String title) {
        String insertSQL = "INSERT INTO " +  username + "(id,title,username,passowrd) VALUES(?,?,?,?)";

        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(insertSQL)) {
            pstmt.setInt(1,id);
            pstmt.setString(2, title);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
