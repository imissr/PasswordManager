import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {


    UserStoreSql userstor = new UserStoreSql();

    // UserStore userStore = new UserStore();
    //LoginSystem loginSystem = new LoginSystem(userstor);
    LoginSystemSql loginSystem = new LoginSystemSql(userstor);
    EntryStore entryStore = new EntryStore();

    public void loginPormpt(){
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

    }

    public void passwordPrompt(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1. add A entry");
            System.out.println("2. deleat Entry");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Username/email : ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();




                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
