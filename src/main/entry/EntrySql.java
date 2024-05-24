import java.sql.*;

public class EntrySql {

    String url = "jdbc:sqlite:E:\\database\\test.db\\";
    int id = 1;

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



    public void insert(String username,String password,String email ,String title) throws SQLException {
        String insertSQL = "INSERT INTO " +  username + "(id,title,username,password) VALUES(?,?,?,?)";

        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(insertSQL)) {
            pstmt.setInt(1,id);
            pstmt.setString(2, title);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.executeUpdate();

        }
        id++;
    }

    public void delete(String username, int id) throws SQLException{
        String deleteQUERY = "DELETE FROM " + username + " WHERE id = ?";
        try( Connection connect = this.connect();
            PreparedStatement pstmt = connect.prepareStatement(deleteQUERY)){
                pstmt.setInt(1,id);
                int rowsAffected = pstmt.executeUpdate();
                if(rowsAffected  > 0 ){
                    System.out.println("entry deleted seccessfully");


            }else{
                    System.out.println("No entry found with the specified ID.");

                }

        }
    }

    public void updateId(String username,int id) throws SQLException{
        String fetchID = "SELECT id FROM " + username + " ORDER BY id";
        try(Connection connect = this.connect();
             PreparedStatement fetch = connect.prepareStatement(fetchID);
            ResultSet rs = fetch.executeQuery()){
            int newID = 1;
            while(rs.next()){
                int currentID = rs.getInt("id");

            }

        }
    }



}
