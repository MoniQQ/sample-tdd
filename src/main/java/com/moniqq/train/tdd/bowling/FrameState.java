package com.moniqq.train.tdd.bowling;

public interface FrameState {

    String print();
    int score();
    boolean isRolled();

    void roll(Score score, int roll);
}
