package com.gmail.gm.jcant.javaPro;

public class Question {
    private String text;
    private int countYes;
    private int countNo;

    public Question() {
        super();
    }

    public Question(String text) {
        super();
        this.text = text;
        this.countYes = 0;
        this.countNo = 0;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCountYes() {
        return countYes;
    }

    public void addCountYes() {
        this.countYes++;
    }

    public int getCountNo() {
        return countNo;
    }

    public void addCountNo() {
        this.countNo++;
    }

    @Override
    public String toString() {
        return "Question{" +
                ", text='" + text + '\'' +
                ", countYes=" + countYes +
                ", countNo=" + countNo +
                '}';
    }
}
