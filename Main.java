import java.util.ArrayList; 
import java.util.Scanner;   

public class Main {

    // Main method -> execution starts from here
    public static void main(String[] args) {

        // Scanner object created for taking input from user
        Scanner sc = new Scanner(System.in);

        // Calling static method from FileManager class
        // File handling used here to load questions from file

        ArrayList<Question> questions =
                FileManager.loadQuestions("questions.txt");

        // Checking if file has questions or not
        // isEmpty() method of ArrayList used here

        if (questions.isEmpty()) {

            System.out.println("No questions found in file!");
            return; // Stops program execution
        }

        // Object creation
        // Constructor called here

        QuizService quizService = new QuizService(questions);

        // Infinite loop used for menu-driven program

        while (true) {

            // Displaying menu

            System.out.println("\n===== ONLINE QUIZ APPLICATION =====");

            System.out.println("1. Start Quiz");
            System.out.println("2. Exit");

            System.out.print("Enter your choice: ");

            int choice = 0;

            // Exception handling used here
            // Prevents crash for invalid inputs

            try {

                // Taking integer input from user
                choice = sc.nextInt();

            } catch (Exception e) {

                System.out.println("Invalid input! Enter numbers only.");

                // Clears invalid input buffer
                sc.nextLine();

                // continue keyword skips current iteration
                continue;
            }

            // Switch case used for menu handling

            switch (choice) {

                case 1:

                    // Calling startQuiz() method
                    // Method calling concept used here

                    quizService.startQuiz();
                    break;

                case 2:

                    System.out.println("Thank You for using Quiz Application!");

                    // Terminates JVM execution
                    System.exit(0);

                default:

                    System.out.println("Invalid choice!");
            }
        }
    }
}