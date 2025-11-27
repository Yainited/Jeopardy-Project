package yainted.manager;

import java.util.ArrayList;
import java.util.HashMap;

import yainted.model.Category;
import yainted.model.Question;

/**
 * Manages the questions and categories in the game.
 */
public class QuestionManager {
    private HashMap<String, Category> categories;
    private int answeredQuestions;

    public QuestionManager() {
        this.categories = new HashMap<>();
        this.answeredQuestions = 0;
    }
    /** Retrieves the categories.
     * @return The HashMap of categories.
     */
    public HashMap<String, Category> getCategories() {
        return categories;
    }
    /** Adds a category to the manager.
     * @param category The Category object to add.
     */
    public void addCategory(Category category) {
        categories.put(category.getName(), category);
    }
    /** Retrieves a question by its ID and category name.
     * @param id The ID of the question.
     * @param categoryName The name of the category.
     * @return The Question object with the specified ID, or null if not found.
     */
    public Question getQuestionByID(String id, String categoryName) {
        Category category = categories.get(categoryName);
        if (category != null) {
            for (Question question : category.getQuestions()) {
                if (question.getId().equals(id)) {
                    return question;
                }
            }
        }
        return null;
    }
    /** Retrieves all questions across all categories.
     * @return The list of all Question objects.
     */
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> allQuestions = new ArrayList<>();
        for (Category category : categories.values()) {
            allQuestions.addAll(category.getQuestions());
        }
        return allQuestions;
    }
    /** Increments the count of answered questions. */
    public void incrementAnsweredQuestions() {
        answeredQuestions++;
    }
    /** Retrieves the count of answered questions.
     * @return The number of answered questions.
     */
    public int getAnsweredQuestions() {
        return answeredQuestions;
    }

}