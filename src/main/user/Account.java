public class Account {
   private String username;
   private String password;
   private EntryStore entry;

    public EntryStore getEntry() {
        return entry;
    }

    public void setEntry(EntryStore entry) {
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
