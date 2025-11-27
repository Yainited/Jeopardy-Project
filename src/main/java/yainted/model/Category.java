package yainted.model;

import java.util.ArrayList;

/**
 * Represents a category containing multiple questions.
 */
public class Category {
    private String name;
    private ArrayList<Question> questions;

    public Category(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    /** Retrieves the name of the category.
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }
    /** Retrieves the questions in the category.
     * @return The list of questions.
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }
    /** Adds a question to the category.
     * @param question The Question object to add.
     */
    public void addQuestion(Question question) {
        questions.add(question);
    }
}
