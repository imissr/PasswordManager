public class LoginSystem {
    private UserStore userStore;

    LoginSystem(UserStore userStore){
        this.userStore = userStore;
    }

    public boolean login(String username , String password){
        Account account = userStore.get_user(username);
        if(account == null){
            System.out.println("User not found please register with a new acc");
            return false;
        }
        if(account.getPassword() == password){
            System.out.println("Login successful!");
            return true;
        }
        else{
            System.out.println("login faild");
            return false;
        }
    }


    public boolean register(String username, String password){
        Account newUser = new Account(username,password);
        boolean if_registerd = userStore.user_register(newUser);
        if(if_registerd){
            System.out.println("User registered successfully!");
            return true;
        }else {
            System.out.println("Username already exists, please choose a different one.");
            return false;
        }
    }



}
