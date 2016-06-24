package com.bss.mintlocker.model;

/**
 * Created by bhawanisingh on 24/11/15.
 */
public class QuestionModal {
    private int uniqueValue;
    private String question;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answeFour;
    private String answerFive;
    private String answerSix;
    private String answerSeven;
    private String answeEight;
    private String answerNine;
    private String answeTen;

    public QuestionModal(int uniqueValue, String question, String answerOne, String answerTwo, String answerThree, String answeFour, String answerFive, String answerSix, String answerSeven, String answeEight, String answerNine, String answeTen) {
        this.uniqueValue = uniqueValue;
        this.question = question;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answeFour = answeFour;
        this.answerFive = answerFive;
        this.answerSix = answerSix;
        this.answerSeven = answerSeven;
        this.answeEight = answeEight;
        this.answerNine = answerNine;
        this.answeTen = answeTen;
    }

    public int getUniqueValue() {
        return uniqueValue;
    }

    public void setUniqueValue(int uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnsweFour() {
        return answeFour;
    }

    public void setAnsweFour(String answeFour) {
        this.answeFour = answeFour;
    }

    public String getAnswerFive() {
        return answerFive;
    }

    public void setAnswerFive(String answerFive) {
        this.answerFive = answerFive;
    }

    public String getAnswerSix() {
        return answerSix;
    }

    public void setAnswerSix(String answerSix) {
        this.answerSix = answerSix;
    }

    public String getAnswerSeven() {
        return answerSeven;
    }

    public void setAnswerSeven(String answerSeven) {
        this.answerSeven = answerSeven;
    }

    public String getAnsweEight() {
        return answeEight;
    }

    public void setAnsweEight(String answeEight) {
        this.answeEight = answeEight;
    }

    public String getAnswerNine() {
        return answerNine;
    }

    public void setAnswerNine(String answerNine) {
        this.answerNine = answerNine;
    }

    public String getAnsweTen() {
        return answeTen;
    }

    public void setAnsweTen(String answeTen) {
        this.answeTen = answeTen;
    }
}
