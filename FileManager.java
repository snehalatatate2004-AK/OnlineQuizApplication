import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class FileManager {

    
    // This method loads questions from the file

    public static ArrayList<Question> loadQuestions(String fileName) {

        // ArrayList used for dynamic question storage

        ArrayList<Question> questionList = new ArrayList<>();

        try {

            
            FileReader fr = new FileReader(fileName);

            BufferedReader br = new BufferedReader(fr);

            String question;

            // Loop runs until the file ends

            while ((question = br.readLine()) != null) {


                String option1 = br.readLine();
                String option2 = br.readLine();
                String option3 = br.readLine();
                String option4 = br.readLine();

                // Converting String to int

                int correctAnswer =
                        Integer.parseInt(br.readLine());

                // here Constructor is called and 
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

                br.readLine();// extra line skip keliy
            }

            br.close();

        } catch (IOException e) {

            System.out.println("Error reading file!");
        }

        return questionList;
    }
}