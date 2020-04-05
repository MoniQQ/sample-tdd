package com.moniqq.train.tdd.bowling.frames;

import com.moniqq.train.tdd.bowling.FrameState;

import java.util.ArrayList;

public class StrikeLastFrame extends Strike implements FrameState {

    @Override
    public String print() {
        ArrayList<Integer> extraRolls = getExtraRolls();
        StringBuilder print = new StringBuilder("X");
        if (extraRolls.size() >= 1) print.append(" ").append(extraRolls.get(0));
        if (extraRolls.size() >= 2) print.append(" ").append(extraRolls.get(1)).append(" |");
        return print.toString();
    }

    @Override
    public boolean isRolled() {
        return getExtraRolls().size() == 2;
    }
}
