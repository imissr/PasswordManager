public class LoginSystemSql {

    private UserStoreSql userstore;

    LoginSystemSql(UserStoreSql userstore){
        this.userstore = userstore;
    }

    public boolean register(String passowrd,String username){
        if(userstore.checkForDuplicateUsername(username)){
            System.out.println("the username exists, please try another username");
            return false;
        }else{
            userstore.insert(new Account(username,passowrd));
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
            if(userstore.getPasswordForUsername(username).equals(password)){
                System.out.println("Login successful!");
                return true;
            }
            return false;

        }


    }
}
