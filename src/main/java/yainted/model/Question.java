package yainted.model;

import java.util.Map;

/**
 * Represents a question in the game.
 */
public class Question {
    private String id;
    private String category;
    private int value;
    private String text;
    private String answer; // correct answer
    private Map<String, String> options; // A/B/C/D

    public Question() {}

    public Question(String category, int value, String text, String answer, Map<String, String> options) {
        this.category = category;
        this.value = value;
        this.text = text;
        this.answer = answer;
        this.options = options;
        this.id = category + "_" + value;
    }

    // getters / setters
    public String getId() { return id; }

    public Map<String, String> getOptions() { return options; }
    public void setOptions(Map<String, String> options) { this.options = options; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public String getOptionValue(String option) {
        return options.get(option);
    }

    @Override
    public String toString() {
        return String.format(
            "Question{category=%s, value=%d, text=%s, answer=%s, options=%s}",
            category, value, text, answer, options
        );
    }
}
