package yainted.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import yainted.model.Question;
import org.w3c.dom.*;
/**
 * Parses quiz questions from an XML file using the DOM API.  
 * <p>
 * Expected XML structure:
 * <pre>
 * &lt;QuestionItem&gt;
 *     &lt;Category&gt;...&lt;/Category&gt;
 *     &lt;QuestionText&gt;...&lt;/QuestionText&gt;
 *     &lt;CorrectAnswer&gt;...&lt;/CorrectAnswer&gt;
 *     &lt;Value&gt;100&lt;/Value&gt;
 *     &lt;Options&gt;
 *         &lt;OptionA&gt;...&lt;/OptionA&gt;
 *         &lt;OptionB&gt;...&lt;/OptionB&gt;
 *         &lt;OptionC&gt;...&lt;/OptionC&gt;
 *         &lt;OptionD&gt;...&lt;/OptionD&gt;
 *     &lt;/Options&gt;
 * &lt;/QuestionItem&gt;
 * </pre>
 * </p>
 */
public class XmlQuestionParser implements QuestionParser {
    /**
     * Parses the XML file into a list of {@link Question} objects.
     *
     * @param file the XML file containing questions
     * @return list of parsed questions
     * @throws RuntimeException if XML parsing fails
     */
    @Override
    public List<Question> parse(File file) {
        List<Question> questions = new ArrayList<>();
        try {
            DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fac.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList qNodes = doc.getElementsByTagName("QuestionItem");
            for (int i = 0; i < qNodes.getLength(); i++) {
                Node qNode = qNodes.item(i);
                if (qNode.getNodeType() != Node.ELEMENT_NODE) continue;
                Element qElem = (Element) qNode;

                String category = getChildText(qElem, "Category");
                String questionText = getChildText(qElem, "QuestionText");
                String answer = getChildText(qElem, "CorrectAnswer");
                String valueStr = getChildText(qElem, "Value");
                int value = 0;
                try { value = Integer.parseInt(valueStr.trim()); } catch (Exception ignored) {}

                Map<String, String> opts = new HashMap<>();
                Element optionsElem = (Element) qElem.getElementsByTagName("Options").item(0);
                if (optionsElem != null) {
                    opts.put("A", getChildText(optionsElem, "OptionA"));
                    opts.put("B", getChildText(optionsElem, "OptionB"));
                    opts.put("C", getChildText(optionsElem, "OptionC"));
                    opts.put("D", getChildText(optionsElem, "OptionD"));
                }

                if (category == null || questionText == null || answer == null) continue;
                questions.add(new Question(category, value, questionText, answer, opts));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse XML: " + e.getMessage(), e);
        }
        return questions;
    }
    /**
     * Returns the trimmed text content of a child element.
     */
    private String getChildText(Element parent, String tagName) {
        NodeList nl = parent.getElementsByTagName(tagName);
        if (nl.getLength() == 0) return null;
        Node n = nl.item(0);
        if (n == null) return null;
        String t = n.getTextContent();
        return t == null ? null : t.trim();
    }
}
