package yainted.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import yainted.model.Question;

/**
 * The QuestionParser interface defines a method for parsing questions from a file.
 */
public interface QuestionParser {

    List<Question> parse(File file) throws IOException;
}
