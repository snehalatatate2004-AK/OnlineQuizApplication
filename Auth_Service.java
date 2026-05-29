import java.io.*;
import java.util.Scanner;

public class Auth_Service {

    private static final String USER_FILE =
            "users.txt";

    // Scanner object

    private static Scanner sc =
            new Scanner(System.in);

    // Registration method

    public static void registerUser() {

        sc.nextLine();

        System.out.println(
                "\n===== USER REGISTRATION =====");

        System.out.print(
                "Enter Username: ");

        String username =
                sc.nextLine();

        System.out.print(
                "Enter Password: ");

        String password =
                sc.nextLine();

        // Checking for duplicate username

        if (isUserExists(username)) {

            System.out.println(
                    "Username already exists!");

            return;
        }

        try {

            FileWriter fw =
                    new FileWriter(
                            USER_FILE,
                            true);

            fw.write(
                    username
                            + ","
                            + password
                            + "\n");

            fw.close();

            System.out.println(
                    "Registration Successful!");

        } catch (IOException e) {

            System.out.println(
                    "Error during registration!");
        }
    }

    // Login method

    public static boolean loginUser() {

        sc.nextLine();

        System.out.println(
                "\n===== USER LOGIN =====");

        System.out.print(
                "Enter Username: ");

        String username =
                sc.nextLine();

        System.out.print(
                "Enter Password: ");

        String password =
                sc.nextLine();

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    USER_FILE));

            String line;

            // Reads the  file line by line

            while ((line = br.readLine())
                    != null) {

                // Split username,password

                String[] data =
                        line.split(",");

                if (data[0].equals(username)
                        &&
                        data[1].equals(password)) {

                    System.out.println(
                            "\nLogin Successful!");

                    br.close();

                    return true;
                }
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                    "Error during login!");
        }

        System.out.println(
                "Invalid Username or Password!");

        return false;
    }

    // Method for checking duplicate users

    public static boolean isUserExists(
            String username) {

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    USER_FILE));

            String line;

            while ((line = br.readLine())
                    != null) {

                String[] data =
                        line.split(",");

                if (data[0].equals(username)) {

                    br.close();

                    return true;
                }
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                    "Error checking username!");
        }

        return false;
    }
}