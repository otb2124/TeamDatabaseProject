//package finalExam;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Signup");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                signup(scanner);
            } else if (choice == 2) {
                login(scanner);
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }

    private static void signup(Scanner scanner) {
        System.out.println("Sign up");
        
        System.out.print("First Name: ");
        String firstName = scanner.next();

        System.out.print("Last Name: ");
        String lastName = scanner.next();

        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        System.out.print("Role (1=Faculty, 2=Student, 3=Public): ");
        int role = scanner.nextInt();

        boolean success = UserAuth.signup(firstName, lastName, email, password, role);

        if (success) {
            System.out.println("Signup successful!");
        } else {
            System.out.println("Signup failed. Email might already exist.");
        }
    }

    private static void login(Scanner scanner) {
        System.out.println("Login");

        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        boolean success = UserAuth.login(email, password);

        if (success) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Check your email and password.");
        }
    }
}

