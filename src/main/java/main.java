import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        UserStoreSql userstor = new UserStoreSql();

        // UserStore userStore = new UserStore();
        //LoginSystem loginSystem = new LoginSystem(userstor);
        LoginSystemSql loginSystem = new LoginSystemSql(userstor);

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    loginSystem.login(loginPassword, loginUsername);
                    break;
                case 2:
                    System.out.print("Enter new username: ");
                    String registerUsername = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String registerPassword = scanner.nextLine();
                    loginSystem.register(registerPassword, registerUsername);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        //Account user1 = new Account("imissr","khaled");

        //userstor.insert(user1);
    }
}
