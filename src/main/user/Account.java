public class Account {
   private String username;
   private String password;
   private EntrySql entry;

    public EntrySql getEntry() {
        return entry;
    }

    public void setEntry(EntrySql entry) {
        this.entry = entry;
    }

    Account(String username , String password ){
       this.username = username;
       this.password = password;


   }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }
}
