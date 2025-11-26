package yainted.game;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

import yainted.model.Question;
import yainted.parser.QuestionParser;
import yainted.parser.QuestionParserFactory;

public class dataInitializer {
    public static ArrayList<Question> getQuestions(String path)
    {
        File f = new File(path);
        try {
            QuestionParser parser = QuestionParserFactory.getParser(path);
            List<Question> questions = parser.parse(f);
            return new ArrayList<>(questions);
        } catch (Exception e) {
            System.err.println("Error parsing file " + path + ": " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
