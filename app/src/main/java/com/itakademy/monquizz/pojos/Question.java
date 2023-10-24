package com.itakademy.monquizz.pojos;

import java.io.Serializable;

public class Question implements Serializable {
    private int id;
    private String text;
    private boolean answer;

    public Question(String text, boolean answer) {
        this.text = text;
        this.answer = answer;
    }

    public Question() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", answer=" + answer +
                '}';
    }
}