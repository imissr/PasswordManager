public class LoginSystemSql {

    private UserStoreSql userstore;
    private Password hasher;

    LoginSystemSql(UserStoreSql userstore , Password hasher){
        this.userstore = userstore;
        this.hasher = hasher;
    }

    public boolean register(String passowrd,String username){
        if(userstore.checkForDuplicateUsername(username)){
            System.out.println("the username exists, please try another username");
            return false;
        }else{
            userstore.insert(new Account(username,hasher.hashPassword(passowrd)));
            userstore.creatSqlTabel(username);
            System.out.println("registred secssufully");
            return true;
        }
    }

    public boolean login(String password,String username){

        if(!userstore.checkForDuplicateUsername(username)) {
            System.out.println("login faild");
            return false;
        }else{
            if(hasher.checkPassword(password, userstore.getPasswordForUsername(username))){
                System.out.println("Login successful!");
                return true;
            }
            return false;

        }


    }
}
