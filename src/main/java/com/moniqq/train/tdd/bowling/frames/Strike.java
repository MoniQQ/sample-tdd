package com.moniqq.train.tdd.bowling.frames;

import com.moniqq.train.tdd.bowling.FrameState;
import com.moniqq.train.tdd.bowling.Score;

import java.util.ArrayList;

public class Strike implements FrameState {

    ArrayList<Integer> extraRolls = new ArrayList<>();

    @Override
    public String print() {
        return "X |";
    }

    @Override
    public int score() {
        if (extraRolls.size() == 2) return 10 + extraRolls.get(0) + extraRolls.get(1);
        return 0;
    }

    @Override
    public boolean isRolled() {
        return true;
    }

    @Override
    public void roll(Score score, int roll) {
        if (extraRolls.size() < 2) extraRolls.add(roll);
    }

    public ArrayList<Integer> getExtraRolls() {
        return extraRolls;
    }
}
