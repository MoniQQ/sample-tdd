package com.moniqq.train.tdd.bowling.frames;

import com.moniqq.train.tdd.bowling.FrameState;
import com.moniqq.train.tdd.bowling.Score;

public class RegularFrame implements FrameState {

    private final int firstRoll;
    private final int secondRoll;

    public RegularFrame(int firstRoll, int secondRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    @Override
    public String print() {
        return String.format("%d %d |", firstRoll, secondRoll);
    }

    @Override
    public int score() {
        return firstRoll + secondRoll;
    }

    @Override
    public boolean isRolled() {
        return true;
    }

    @Override
    public void roll(Score score, int roll) {
    }
}
