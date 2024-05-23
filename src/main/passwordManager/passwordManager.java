import java.util.Set;

public class passwordManager {
    private EntrySql entrysql;

    passwordManager(EntrySql entrysql){
        this.entrysql = entrysql;
    }


    public Entry creatEntry(String email, String title,String password){
        return new Entry(title, email,password);
    }

    public boolean addEntryToDataBase(){

    }





}
