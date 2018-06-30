package com.thunderhook.ragescore.entity;

import java.util.List;

public class Round {

    private int number;

    private List<Score> scores;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
