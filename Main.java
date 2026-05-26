import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    // Constant variable used here
    // Stores file name

    private static final String FILE_NAME =
            "questions.txt";

    // Single Scanner object
    // Best practice used in industry

    private static Scanner sc =
            new Scanner(System.in);

    // Main method -> execution starts here

    public static void main(String[] args) {

        // Load questions from file

        ArrayList<Question> questions =
                FileManager.loadQuestions(FILE_NAME);

        // Check if questions exist

        if (questions.isEmpty()) {

            System.out.println(
                    "No questions found in file!");
        }

        // Object creation

        QuizService quizService =
                new QuizService(questions);

        // Infinite loop for menu

        while (true) {

            System.out.println(
                    "\n===== ONLINE QUIZ APPLICATION =====");

            System.out.println("1. Start Quiz");

            System.out.println("2. Admin Panel");

            System.out.println("3. Exit");

            System.out.print(
                    "Enter your choice: ");

            int choice = 0;

            // Exception handling

            try {

                choice = sc.nextInt();

            } catch (Exception e) {

                System.out.println(
                        "Invalid input! Enter numbers only.");

                sc.nextLine();

                continue;
            }

            // Switch case

            switch (choice) {

                case 1:

                    // Start quiz

                    quizService.startQuiz();

                    break;

                case 2:

                    // Open admin panel

                    adminPanel();

                    // Reload updated questions

                    questions =
                            FileManager.loadQuestions(
                                    FILE_NAME);

                    // Recreate object

                    quizService =
                            new QuizService(questions);

                    break;

                case 3:

                    System.out.println(
                            "Thank You for using Quiz Application!");

                    System.exit(0);

                default:

                    System.out.println(
                            "Invalid choice!");
            }
        }
    }

    // Admin panel method

    public static void adminPanel() {

        // Admin password

        String adminPassword = "admin123";

        sc.nextLine();

        System.out.print(
                "\nEnter Admin Password: ");

        String enteredPassword =
                sc.nextLine();

        // Password validation

        if (!enteredPassword.equals(adminPassword)) {

            System.out.println(
                    "Access Denied!");

            return;
        }

        System.out.println(
                "\n===== ADMIN PANEL =====");

        System.out.println("1. Add Question");

        System.out.println("2. View Questions");

        System.out.println("3. Delete Question");

        System.out.println("4. Exit");

        System.out.print(
                "Enter your choice: ");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:

                addQuestion();

                break;

            case 2:

                viewQuestions();

                break;

            case 3:

                deleteQuestion();

                break;

            case 4:

                System.out.println(
                        "Exiting Admin Panel");

                break;

            default:

                System.out.println(
                        "Invalid choice!");
        }
    }

    // Method used to add question

    public static void addQuestion() {

        sc.nextLine();

        try {

            // Append mode enabled

            FileWriter fw =
                    new FileWriter(
                            FILE_NAME,
                            true);

            System.out.println(
                    "\nEnter Question:");

            String question =
                    sc.nextLine();

            System.out.println(
                    "Enter Option 1:");

            String option1 =
                    sc.nextLine();

            System.out.println(
                    "Enter Option 2:");

            String option2 =
                    sc.nextLine();

            System.out.println(
                    "Enter Option 3:");

            String option3 =
                    sc.nextLine();

            System.out.println(
                    "Enter Option 4:");

            String option4 =
                    sc.nextLine();

            System.out.println(
                    "Enter Correct Answer Number (1-4):");

            int correctAnswer =
                    sc.nextInt();

            // Writing data into file

            fw.write("\n");

            fw.write(question + "\n");

            fw.write(option1 + "\n");

            fw.write(option2 + "\n");

            fw.write(option3 + "\n");

            fw.write(option4 + "\n");

            fw.write(correctAnswer + "\n");

            // Close resource

            fw.close();

            System.out.println(
                    "\nQuestion Added Successfully!");

        } catch (IOException e) {

            System.out.println(
                    "Error while adding question!");
        }
    }

    // Method used to display questions

    public static void viewQuestions() {

        ArrayList<Question> questions =
                FileManager.loadQuestions(FILE_NAME);

        System.out.println(
                "\n===== ALL QUESTIONS =====");

        // Enhanced for loop

        for (Question q : questions) {

            System.out.println(
                    "\nQuestion: "
                            + q.getQuestion());

            System.out.println(
                    "1. " + q.getOption1());

            System.out.println(
                    "2. " + q.getOption2());

            System.out.println(
                    "3. " + q.getOption3());

            System.out.println(
                    "4. " + q.getOption4());

            System.out.println(
                    "Correct Answer: "
                            + q.getCorrectOptionText());
        }
    }

    // Method used to delete question

    public static void deleteQuestion() {

        ArrayList<Question> questions =
                FileManager.loadQuestions(FILE_NAME);

        // Check if empty

        if (questions.isEmpty()) {

            System.out.println(
                    "No questions available!");

            return;
        }

        System.out.println(
                "\n===== DELETE QUESTION =====");

        // Display questions

        for (int i = 0;
             i < questions.size();
             i++) {

            System.out.println(
                    (i + 1)
                            + ". "
                            + questions.get(i)
                            .getQuestion());
        }

        System.out.print(
                "Enter question number to delete: ");

        int deleteIndex =
                sc.nextInt() - 1;

        // Validation

        if (deleteIndex < 0
                || deleteIndex >= questions.size()) {

            System.out.println(
                    "Invalid question number!");

            return;
        }

        // Remove question

        questions.remove(deleteIndex);

        try {

            // Rewrite entire file

            FileWriter fw =
                    new FileWriter(FILE_NAME);

            // Save updated data

            for (Question q : questions) {

                fw.write(
                        q.getQuestion() + "\n");

                fw.write(
                        q.getOption1() + "\n");

                fw.write(
                        q.getOption2() + "\n");

                fw.write(
                        q.getOption3() + "\n");

                fw.write(
                        q.getOption4() + "\n");

                fw.write(
                        q.getCorrectAnswer()
                                + "\n\n");
            }

            fw.close();

            System.out.println(
                    "Question Deleted Successfully!");

        } catch (IOException e) {

            System.out.println(
                    "Error deleting question!");
        }
    }
}