package com.moniqq.train.tdd.bowling.frames;

import com.moniqq.train.tdd.bowling.FrameState;
import com.moniqq.train.tdd.bowling.Score;

public class Spare implements FrameState {

    private final int firstRoll;
    private Integer extraRoll;

    public Spare(int firstRoll) {
        this.firstRoll = firstRoll;
    }

    @Override
    public String print() {
        return String.format("%d / |", firstRoll);
    }

    @Override
    public int score() {
        if (extraRoll != null) return 10 + extraRoll;
        return firstRoll;
    }

    @Override
    public boolean isRolled() {
        return true;
    }

    @Override
    public void roll(Score score, int roll) {
        if (extraRoll == null) extraRoll = roll;
    }

    public int getFirstRoll() {
        return firstRoll;
    }

    public Integer getExtraRoll() {
        return extraRoll;
    }
}
