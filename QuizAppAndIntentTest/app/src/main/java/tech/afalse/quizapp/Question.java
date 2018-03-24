package tech.afalse.quizapp;

/**
 * Created by aqali on 10.03.2018.
 */


public class Question {
    private String text;
    private boolean answer;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public Question(String text, boolean answer) {
        this.text = text;
        this.answer = answer;
    }
}
