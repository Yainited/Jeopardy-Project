package yainted;

import java.util.List;
import java.util.Locale.Category;

import model.Question;

public class QuestionManager {
    private List<Question> questions;
    private Question currentQuestion;

    public QuestionManager(List<Question> questions) {
        this.questions = questions;
    }

    public Question getQuestionById(String id) {
        for (Question q : questions) {
            if (q.getId().equals(id)) {
                return q;
            }
        }
        return null;
    }

    public Question getQuestionByValue(int value, String category) {
        Question question = questions.stream()
            .filter(q -> q.getValue() == value && !q.isUsed() && q.getCategory().equalsIgnoreCase(category))
            .findFirst()
            .orElse(null);
        return question;
    }

    public void setCurrentQuestion(String questionId) {
        this.currentQuestion = getQuestionById(questionId);
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}