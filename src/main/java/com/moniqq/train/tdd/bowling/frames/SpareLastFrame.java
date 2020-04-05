package com.moniqq.train.tdd.bowling.frames;

import com.moniqq.train.tdd.bowling.FrameState;

public class SpareLastFrame extends Spare implements FrameState {

    public SpareLastFrame(int firstRoll) {
        super(firstRoll);
    }

    @Override
    public String print() {
        if (getExtraRoll() == null) return String.format("%d /", getFirstRoll());
        else return String.format("%d / %d |", getFirstRoll(), getExtraRoll());
    }

    @Override
    public boolean isRolled() {
        return getExtraRoll() != null;
    }
}
