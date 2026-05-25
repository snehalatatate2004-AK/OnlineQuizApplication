import java.util.ArrayList;
import java.util.Scanner;

public class QuizService {

    // ArrayList reference variable
    // Stores all question objects

    private ArrayList<Question> questionList;

    // Stores user score

    private int score = 0;

    // Constructor used here
    // Constructor initializes question list

    public QuizService(ArrayList<Question> questions) {

        this.questionList = questions;
    }

    // Method used to start quiz

    public void startQuiz() {

        // Reset score before starting new quiz
        score = 0;

        Scanner sc = new Scanner(System.in);

        // ArrayList stores wrong answered questions

        ArrayList<String> wrongAnswers =
                new ArrayList<>();

        System.out.println("\n===== QUIZ STARTED =====\n");

        // for loop used for traversing ArrayList

        for (int i = 0; i < questionList.size(); i++) {

            // Fetching current question object

            Question q = questionList.get(i);

            // Displaying question

            System.out.println("Question " + (i + 1));

            System.out.println(q.getQuestion());

            // Displaying options

            System.out.println("1. " + q.getOption1());

            System.out.println("2. " + q.getOption2());

            System.out.println("3. " + q.getOption3());

            System.out.println("4. " + q.getOption4());

            int userAnswer = 0;

            // Infinite loop for input validation

            while (true) {

                try {

                    System.out.print("Enter your answer (1-4): ");

                    // Taking integer input

                    userAnswer = sc.nextInt();

                    // Validation logic

                    if (userAnswer < 1 || userAnswer > 4) {

                        // Custom exception throwing

                        throw new IllegalArgumentException();
                    }

                    // Break loop if valid input

                    break;

                } catch (IllegalArgumentException e) {

                    System.out.println(
                            "Answer must be between 1 and 4");

                } catch (Exception e) {

                    System.out.println(
                            "Invalid input! Numbers only.");

                    // Clears invalid input buffer

                    sc.nextLine();
                }
            }

            // Answer checking logic

            if (userAnswer == q.getCorrectAnswer()) {

                score++;

                System.out.println("Correct Answer!\n");

            } else {

                System.out.println("Wrong Answer!");

                System.out.println(
                        "Correct Answer is: "
                        + q.getCorrectAnswer());

                // Adding wrong question into ArrayList

                wrongAnswers.add(q.getQuestion());

                System.out.println();
            }
        }

        // Calling result method

        showResult(wrongAnswers);
    }

    // Method used for displaying final result

    public void showResult(ArrayList<String> wrongAnswers) {

        int totalQuestions = questionList.size();

        // Type casting used here

        double percentage =
                ((double) score / totalQuestions) * 100;

        System.out.println("\n===== FINAL RESULT =====");

        System.out.println(
                "Total Questions : " + totalQuestions);

        System.out.println(
                "Correct Answers : " + score);

        System.out.println(
                "Wrong Answers   : "
                        + (totalQuestions - score));

        System.out.println(
                "Percentage      : "
                        + percentage + "%");

        // Grade calculation logic

        if (percentage >= 80) {

            System.out.println("Grade : Excellent");

        } else if (percentage >= 60) {

            System.out.println("Grade : Good");

        } else if (percentage >= 40) {

            System.out.println("Grade : Average");

        } else {

            System.out.println("Grade : Need Improvement");
        }

        // Displaying wrong answered questions

        if (!wrongAnswers.isEmpty()) {

            System.out.println(
                    "\nWrongly Answered Questions:");

            // Enhanced for loop used here

            for (String question : wrongAnswers) {

                System.out.println("- " + question);
            }
        }
    }
}