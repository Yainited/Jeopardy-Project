package yainted.game;

import java.util.ArrayList;

import yainted.model.Question;

public class QuestionManager {
    private ArrayList<Question> questions;
    private Question currentQuestion;

    public QuestionManager(ArrayList<Question> questions) {
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

    public void setCurrentQuestion(String questionId) {
        this.currentQuestion = getQuestionById(questionId);
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}