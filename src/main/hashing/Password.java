import org.mindrot.jbcrypt.BCrypt;

public class Password {
    private  Password(){
    }

    private static class Holder {
        private static final Password INSTANCE = new Password();
    }

    public static Password getInstance() {
        return Holder.INSTANCE;
    }

    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Method to check a password against a hashed password
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);

    }


}
