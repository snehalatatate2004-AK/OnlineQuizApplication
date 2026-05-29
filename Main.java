import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static final String FILE_NAME =
            "questions.txt";

    // Single Scanner object is used instead of using multiple

    private static Scanner sc =
            new Scanner(System.in);
    public static void main(String[] args) {

        // Here questions are load from the file

        ArrayList<Question> questions =
                FileManager.loadQuestions(FILE_NAME);

        if (questions.isEmpty()) {

            System.out.println(
                    "No questions found in file!");

                 return;
        }

        QuizService quizService =
                new QuizService(questions);

        // Infinite loop is used for the menu

        while (true) {

            System.out.println(
                    "\n...ONLINE QUIZ APPLICATION...");

            System.out.println("1. Register");

            System.out.println("2. Login");

            System.out.println("3. Admin Panel");

            System.out.println("4. Exit");

            System.out.print(
                    "Enter your choice: ");

            int choice = 0;

            try {

                choice = sc.nextInt();

            } catch (Exception e) {

                System.out.println(
                        "Invalid input! Enter numbers only.");

                sc.nextLine();

                continue;
            }

            switch (choice) {

    case 1:

        // Register new user

        Auth_Service.registerUser();

        break;

    case 2:

        // Login validation

        boolean loginSuccess =
                Auth_Service.loginUser();

        // Start quiz only if login success

        if (loginSuccess) {

            quizService.startQuiz();
        }

        break;

    case 3:

        adminPanel();

        // Reload updated questions

        questions =
                FileManager.loadQuestions(
                        FILE_NAME);

        // Recreate object

        quizService =
                new QuizService(questions);

        break;

    case 4:

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

        String adminPassword = "admin123";

        sc.nextLine();

        System.out.print(
                "\nEnter Admin Password: ");

        String enteredPassword =
                sc.nextLine();

        if (!enteredPassword.equals(adminPassword)) {

            System.out.println(
                    "Access Denied!");

            return;
        }

        System.out.println(
                "\n...ADMIN PANEL...");

        System.out.println("1. Add Question");

        System.out.println("2. View Questions");

        System.out.println("3. Delete Question");

        System.out.println("4. Exit");

        System.out.println("5. View Score History");

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

            case 5:

              viewScoreHistory();

                break;

            default:

                System.out.println(
                        "Invalid choice!");
        }
    }

    // Method used to add questions

    public static void addQuestion() {

        sc.nextLine();

        try {

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
                "\n...ALL QUESTIONS...");

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
                "\n...DELETE QUESTION...");

        // for displaying questions

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

        if (deleteIndex < 0
                || deleteIndex >= questions.size()) {

            System.out.println(
                    "Invalid question number!");

            return;
        }

        questions.remove(deleteIndex);

        try {

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
    // Method used to display score history

    public static void viewScoreHistory() {

    System.out.println(
            "\n...SCORE HISTORY...");

    try {

        // BufferedReader used for reading file

        java.io.BufferedReader br =
                new java.io.BufferedReader(
                        new java.io.FileReader(
                                "scoreHistory.txt"));

        String line;

        // Read file line by line

        while ((line = br.readLine()) != null) {

            System.out.println(line);
        }

        br.close();

    } catch (IOException e) {

        System.out.println(
                "Error reading score history!");
    }
}
}