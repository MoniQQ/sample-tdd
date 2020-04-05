package com.moniqq.train.tdd.bowling.frames;

import com.moniqq.train.tdd.bowling.FrameState;
import com.moniqq.train.tdd.bowling.Score;
import com.moniqq.train.tdd.bowling.exceptions.PinsOutOfBoundsException;

public class NewFrame implements FrameState {

    @Override
    public String print() {
        return "";
    }

    @Override
    public int score() {
        return 0;
    }

    @Override
    public boolean isRolled() {
        return false;
    }

    @Override
    public void roll(Score score, int roll) {
        if (roll > 10) throw new PinsOutOfBoundsException();

        if (roll == 10) {
            if (score.isLastFrame()) score.setCurrentFrame(new StrikeLastFrame());
                else score.setCurrentFrame(new Strike());
        }
        else score.setCurrentFrame(new FrameInProgress(roll));
    }
}
