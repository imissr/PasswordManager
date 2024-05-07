import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private Map<String,Account> users = new HashMap<>();

    public UserStore(){

    }

    public void put_user(Account account){
        users.put(account.getUsername(),new Account(account.getUsername(),account.getPassword()));
    }

    public boolean user_register(Account account){
        if(users.containsKey(account.getUsername())){
            System.out.println("the username exists, please try another username");
            return false;
        }
        put_user(account);
        return true;
    }

    public Account get_user(String username){
        return users.get(username);

    }




}
