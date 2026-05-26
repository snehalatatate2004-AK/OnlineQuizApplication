import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class QuizService {

    // Constant variable
    // Stores score history file name

    private static final String SCORE_FILE =
            "scoreHistory.txt";

    // Single Scanner object
    // Best practice

    private static Scanner sc =
            new Scanner(System.in);

    // Stores all question objects

    private ArrayList<Question> questionList;

    // Stores user score

    private int score;

    // Constructor used here

    public QuizService(
            ArrayList<Question> questions) {

        this.questionList = questions;
    }

    // Method used to start quiz

    public void startQuiz() {

        // Reset score

        score = 0;

        // Randomize question order

        Collections.shuffle(questionList);

        // Start timer

        long startTime =
                System.currentTimeMillis();

        // Stores wrong answered questions

        ArrayList<String> wrongAnswers =
                new ArrayList<>();

        System.out.println(
                "\n===== QUIZ STARTED =====\n");

        // Traversing question list

        for (int i = 0;
             i < questionList.size();
             i++) {

            // Fetch current question

            Question currentQuestion =
                    questionList.get(i);

            // Display question

            displayQuestion(
                    currentQuestion,
                    i + 1);

            // Take validated answer

            int userAnswer =
                    getValidatedAnswer();

            // Check answer

            checkAnswer(
                    currentQuestion,
                    userAnswer,
                    wrongAnswers);
        }

        // End timer

        long endTime =
                System.currentTimeMillis();

        // Calculate total time

        long totalTime =
                (endTime - startTime) / 1000;

        // Show final result

        showResult(
                wrongAnswers,
                totalTime);
    }

    // Method used to display question

    public void displayQuestion(
            Question q,
            int questionNumber) {

        System.out.println(
                "Question " + questionNumber);

        System.out.println(
                q.getQuestion());

        System.out.println(
                "1. " + q.getOption1());

        System.out.println(
                "2. " + q.getOption2());

        System.out.println(
                "3. " + q.getOption3());

        System.out.println(
                "4. " + q.getOption4());
    }

    // Method used for validated input

    public int getValidatedAnswer() {

        int userAnswer;

        while (true) {

            try {

                System.out.print(
                        "Enter your answer (1-4): ");

                userAnswer =
                        sc.nextInt();

                // Validation

                if (userAnswer < 1
                        || userAnswer > 4) {

                    throw new IllegalArgumentException();
                }

                return userAnswer;

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Answer must be between 1 and 4");

            } catch (Exception e) {

                System.out.println(
                        "Invalid input! Numbers only.");

                sc.nextLine();
            }
        }
    }

    // Method used for answer checking

    public void checkAnswer(
            Question q,
            int userAnswer,
            ArrayList<String> wrongAnswers) {

        if (userAnswer
                == q.getCorrectAnswer()) {

            score++;

            System.out.println(
                    "Correct Answer!\n");

        } else {

            System.out.println(
                    "Wrong Answer!");

            System.out.println(
                    "Correct Answer is: "
                            + q.getCorrectOptionText());

            // Store wrong question

            wrongAnswers.add(
                    q.getQuestion());

            System.out.println();
        }
    }

    // Method used to display result

    public void showResult(
            ArrayList<String> wrongAnswers,
            long totalTime) {

        int totalQuestions =
                questionList.size();

        // Percentage calculation

        double percentage =
                ((double) score
                        / totalQuestions) * 100;

        // Save history

        saveScoreHistory(percentage);

        System.out.println(
                "\n===== FINAL RESULT =====");

        System.out.println(
                "Total Questions : "
                        + totalQuestions);

        System.out.println(
                "Correct Answers : "
                        + score);

        System.out.println(
                "Wrong Answers   : "
                        + (totalQuestions - score));

        System.out.println(
                "Percentage      : "
                        + percentage + "%");

        System.out.println(
                "Time Taken      : "
                        + totalTime + " seconds");

        // Display grade

        System.out.println(
                "Grade : "
                        + calculateGrade(percentage));

        // Display wrong answers

        displayWrongAnswers(wrongAnswers);
    }

    // Method used to calculate grade

    public String calculateGrade(
            double percentage) {

        if (percentage >= 80) {

            return "Excellent";

        } else if (percentage >= 60) {

            return "Good";

        } else if (percentage >= 40) {

            return "Average";
        }

        return "Need Improvement";
    }

    // Method used to display wrong answers

    public void displayWrongAnswers(
            ArrayList<String> wrongAnswers) {

        if (!wrongAnswers.isEmpty()) {

            System.out.println(
                    "\nWrongly Answered Questions:");

            for (String question
                    : wrongAnswers) {

                System.out.println(
                        "- " + question);
            }
        }
    }

    // Method used to save score history

    public void saveScoreHistory(
            double percentage) {

        sc.nextLine();

        System.out.print(
                "Enter your name: ");

        String name =
                sc.nextLine();

        try {

            // Append mode enabled

            FileWriter fw =
                    new FileWriter(
                            SCORE_FILE,
                            true);

            fw.write(
                    "Name : "
                            + name
                            + " | Score : "
                            + percentage
                            + "%\n");

            // Close resource

            fw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving score history!");
        }
    }
}