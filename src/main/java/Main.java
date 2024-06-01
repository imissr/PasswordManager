import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    UserStoreSql userstor = new UserStoreSql();
    EntrySql entrySql = new EntrySql();
    PasswordManager passwordManager = new PasswordManager(entrySql);

    LoginSystemSql loginSystem = new LoginSystemSql(userstor);


    public void loginPormpt(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        while(true){
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String loginPassword = scanner.nextLine();
                        if(!loginSystem.login(loginPassword.toString(), loginUsername))
                            break;
                        else passwordPrompt(loginUsername);
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
            }catch(InputMismatchException e){
                System.out.println("Invalid input");
                loginPormpt();
            }

        }

    }

    public void passwordPrompt(String username){
        Scanner scanner = new Scanner(System.in);
        int choice;

        try {


            while (true) {
                System.out.println("1. add A entry");
                System.out.println("2. deleat Entry");
                System.out.println("3. see sepcfic entry");
                System.out.println("4. show all entry again");
                System.out.println("5. delete account");
                System.out.println("5. sign out");
                passwordManager.returnAllEntrypassword(username);
                System.out.print("Choose an option: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline


                switch (choice) {
                    case 1:
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Username/email : ");
                        String email = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        passwordManager.addEntryToDataBase(username, passwordManager.creatEntry(email, title, password));
                        break;

                    case 2:
                        System.out.print("Enter id: ");
                        int id = scanner.nextInt();
                        passwordManager.deleatEntryFromDataBase(username, id);
                        break;
                    case 3:
                        System.out.print("Enter id: ");
                        int id1 = scanner.nextInt();
                        passwordManager.returnspecficEntry(username, id1);
                        break;
                    case 4:
                        passwordManager.returnAllEntrypassword(username);
                        break;

                    case 5:
                        if (passwordManager.deleteAccount(username))
                            loginPormpt();
                        else break;


                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }catch(InputMismatchException e ){
            System.out.println("Invalid input");
            passwordPrompt(username);
        }
    }
}
