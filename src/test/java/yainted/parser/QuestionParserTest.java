package yainted.parser;

import org.junit.jupiter.api.Test;
import yainted.model.Question;
import yainted.parser.CsvQuestionParser;
import yainted.parser.JsonQuestionParser;
import yainted.parser.XmlQuestionParser;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionParserTest {

    private File writeTempFile(String name, String content) throws Exception {
        File f = File.createTempFile(name, ".csv"); 
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(content);
        }
        f.deleteOnExit(); 
        return f;
    }

    @Test
    public void testParseValidCSV() throws Exception {
        String csvContent = "Category,Value,Question,OptionA,OptionB,OptionC,OptionD,CorrectAnswer\n" +
                            "Valorant,100,What is the best agent?,Viper,Brimstone,Yoru,Iso,D";

        File tempFile = writeTempFile("sample_game_CSV", csvContent);

        CsvQuestionParser parser = new CsvQuestionParser();
        List<Question> questions = parser.parse(tempFile); 

        assertNotNull(questions, "Parsed questions should not be null");
        assertFalse(questions.isEmpty(), "Parsed questions should not be empty");

        Question q = questions.get(0);
        assertEquals("Valorant", q.getCategory());
        assertEquals(100, q.getValue());
        assertEquals("What is the best agent?", q.getText());
        assertEquals("D", q.getAnswer());
        assertEquals("Viper", q.getOptions().get("A"));
        assertEquals("Brimstone", q.getOptions().get("B"));
        assertEquals("Yoru", q.getOptions().get("C"));
        assertEquals("Iso", q.getOptions().get("D"));
    }

    private File writeTempFile1(String name, String content) throws Exception {
        File f = File.createTempFile(name, ".json");
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(content);
        }
        f.deleteOnExit();
        return f;
    }

    @Test
    public void testParseValorantVandalQuestion() throws Exception {
        String jsonContent = "[\n" +
                "  {\n" +
                "    \"Category\": \"Valorant\",\n" +
                "    \"Value\": 100,\n" +
                "    \"Question\": \"What is the best Vandal skin?\",\n" +
                "    \"Options\": {\n" +
                "      \"A\": \"Prime\",\n" +
                "      \"B\": \"Elderflame\",\n" +
                "      \"C\": \"Reaver\",\n" +
                "      \"D\": \"Oni\"\n" +
                "    },\n" +
                "    \"CorrectAnswer\": \"A\"\n" +
                "  }\n" +
                "]";

        File tempFile = writeTempFile1("valorant_vandal_JSON", jsonContent);

        JsonQuestionParser parser = new JsonQuestionParser();
        List<Question> questions = parser.parse(tempFile);

        assertNotNull(questions);
        assertFalse(questions.isEmpty());

        Question q = questions.get(0);
        assertEquals("Valorant", q.getCategory());
        assertEquals(100, q.getValue());
        assertEquals("What is the best Vandal skin?", q.getText());
        assertEquals("A", q.getAnswer());
        assertEquals("Prime", q.getOptions().get("A"));
        assertEquals("Elderflame", q.getOptions().get("B"));
        assertEquals("Reaver", q.getOptions().get("C"));
        assertEquals("Oni", q.getOptions().get("D"));
    }
}
