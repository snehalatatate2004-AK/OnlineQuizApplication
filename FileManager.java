import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class FileManager {

    // Static method used here
    // This method loads questions from file

    public static ArrayList<Question> loadQuestions(String fileName) {

        // ArrayList used for dynamic question storage

        ArrayList<Question> questionList = new ArrayList<>();

        try {

            // FileReader reads file characters
            FileReader fr = new FileReader(fileName);

            // BufferedReader improves performance
            BufferedReader br = new BufferedReader(fr);

            String question;

            // Loop runs until file ends

            while ((question = br.readLine()) != null) {

                // Reading options from file

                String option1 = br.readLine();
                String option2 = br.readLine();
                String option3 = br.readLine();
                String option4 = br.readLine();

                // Converting String to int

                int correctAnswer =
                        Integer.parseInt(br.readLine());

                // Constructor called here
                // Object creation

                Question q = new Question(
                        question,
                        option1,
                        option2,
                        option3,
                        option4,
                        correctAnswer
                );

                // Adding object into ArrayList

                questionList.add(q);

                // Extra line skipped between questions

                br.readLine();
            }

            // Closing resources
            // Best practice in industry

            br.close();

        } catch (IOException e) {

            // Exception handling used here

            System.out.println("Error reading file!");
        }

        // Returning question list

        return questionList;
    }
}