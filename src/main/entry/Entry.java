public class Entry {

    public String title;
    public String userName;
    public String password;

    Entry(String title, String userName, String password){
        this.password = password;
        this.userName = userName;
        this.title = title;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
