package com.thunderhook.ragescore.entity;

public class Score {

    private Player player;

    private int value;

    public Score(Player player, int value) {
        this.player = player;
        this.value = value;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
