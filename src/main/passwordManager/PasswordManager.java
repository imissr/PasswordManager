import java.sql.SQLException;

public class PasswordManager {
    private final EntrySql entrysql;

    PasswordManager(EntrySql entrysql){
        this.entrysql = entrysql;
    }


    public Entry creatEntry(String email, String title,String password) {
        return new Entry(title, email, password);
    }


    public boolean addEntryToDataBase(String username, Entry entry){
        try {
            entrysql.insert(username,entry.getPassword(), entry.getUserName() ,entry.getTitle());

            System.out.println("entry got added seccsfully");
            return true;

        } catch (SQLException e) {
            System.out.println("faild to add a entry");
            return false;
        }
    }


    public boolean deleatEntryFromDataBase(String username,int id){
        try {
            entrysql.delete(username,id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }










}
