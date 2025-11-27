package yainted.model;

import java.util.Map;
/**
 * Represents a single Jeopardy-style question.
 * 
 * <p>This model stores the question's category, monetary value, text prompt,
 * correct answer, and multiple-choice options (A/B/C/D). It is used by all
 * parsers (CSV, JSON, XML) and by the game engine to deliver questions to
 * players.</p>
 *
 * <p>The question ID is auto-generated using the format:
 * <b>Category + "_" + Value</b></p>
 */
public class Question {
    private String id;
    private String category;
    private int value;
    private String text;
    private String answer; // correct answer
    private Map<String, String> options; // A/B/C/D
    /**
     * Default no-argument constructor.
     */
    public Question() {}
    /**
     * Creates a new Question instance.
     *
     * @param category the category the question belongs to
     * @param value the point value of the question
     * @param text the question text
     * @param answer the correct answer
     * @param options a map of the multiple-choice options (A/B/C/D)
     */
    public Question(String category, int value, String text, String answer, Map<String, String> options) {
        this.category = category;
        this.value = value;
        this.text = text;
        this.answer = answer;
        this.options = options;
        this.id = category + "_" + value;
    }

    /**
     * Returns the unique ID of the question.
     *
     * @return the question ID
     */
    public String getId() { return id; }
    /**
     * Returns all answer options.
     *
     * @return a map of options labeled A/B/C/D
     */
    public Map<String, String> getOptions() { return options; }
    /**
     * Sets or updates the available options.
     *
     * @param options the map of A/B/C/D options
     */
    public void setOptions(Map<String, String> options) { this.options = options; }
    /**
     * Gets the question’s category.
     *
     * @return the category string
     */
    public String getCategory() { return category; }
    /**
     * Updates the question category.
     *
     * @param category the new category
     */
    public void setCategory(String category) { this.category = category; }
    /**
     * Gets the question value.
     *
     * @return the monetary value
     */
    public int getValue() { return value; }
    /**
     * Sets the point value of the question.
     *
     * @param value the monetary value
     */
    public void setValue(int value) { this.value = value; }
    /**
     * Gets the main text of the question.
     *
     * @return the question text
     */
    public String getText() { return text; }
    /**
     * Sets the question text.
     *
     * @param text the new question text
     */
    public void setText(String text) { this.text = text; }
    /**
     * Returns the correct answer.
     *
     * @return the correct answer string
     */
    public String getAnswer() { return answer; }
        /**
     * Sets the correct answer.
     *
     * @param answer the new correct answer
     */
    public void setAnswer(String answer) { this.answer = answer; }
    /**
     * Retrieves the text for a specific option choice.
     *
     * @param option the option key ("A", "B", "C", or "D")
     * @return the option text, or null if missing
     */
    public String getOptionValue(String option) {
        return options.get(option);
    }
    /**
     * Returns a string representation of the question for debugging.
     *
     * @return a formatted string containing all fields
     */
    @Override
    public String toString() {
        return String.format(
            "Question{category=%s, value=%d, text=%s, answer=%s, options=%s}",
            category, value, text, answer, options
        );
    }
}
