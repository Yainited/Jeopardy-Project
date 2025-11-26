package yainted.game;

import java.util.ArrayList;

import yainted.model.Question;

/**
 * The QuestionManager class manages the list of questions and the current question in the game.
 */
public class QuestionManager {
    private ArrayList<Question> questions;
    private Question currentQuestion;

    public QuestionManager(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /** Retrieves a question by its ID.
     * @param id The ID of the question.
     * @return The Question object.
     */
    public Question getQuestionById(String id) {
        for (Question q : questions) {
            if (q.getId().equals(id)) {
                return q;
            }
        }
        return null;
    }

    /** Sets the current question by its ID.
     * @param questionId The ID of the question to set as current.
     */
    public void setCurrentQuestion(String questionId) {
        this.currentQuestion = getQuestionById(questionId);
    }

    /** Retrieves the current question.
     * @return The current Question object.
     */
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    /** Retrieves the list of questions.
     * @return The list of Question objects.
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }
}