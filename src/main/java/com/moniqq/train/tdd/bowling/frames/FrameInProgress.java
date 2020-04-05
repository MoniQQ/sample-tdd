package com.moniqq.train.tdd.bowling.frames;

import com.moniqq.train.tdd.bowling.FrameState;
import com.moniqq.train.tdd.bowling.Score;
import com.moniqq.train.tdd.bowling.exceptions.PinsOutOfBoundsException;

import static java.lang.String.valueOf;

public class FrameInProgress implements FrameState {
    private final int firstRoll;

    public FrameInProgress(int roll) {
        this.firstRoll = roll;
    }

    @Override
    public String print() {
        return valueOf(firstRoll);
    }

    @Override
    public int score() {
        return firstRoll;
    }

    @Override
    public boolean isRolled() {
        return false;
    }

    @Override
    public void roll(Score score, int roll) {
        if (firstRoll + roll > 10) throw new PinsOutOfBoundsException();
        if (firstRoll + roll == 10)
            if (score.isLastFrame()) score.setCurrentFrame(new SpareLastFrame(firstRoll));
                else score.setCurrentFrame(new Spare(firstRoll));
        else score.setCurrentFrame(new RegularFrame(firstRoll, roll));
    }
}
