package com.moniqq.train.tdd.bowling;


import com.moniqq.train.tdd.bowling.exceptions.PinsOutOfBoundsException;
import com.moniqq.train.tdd.bowling.frames.NewFrame;

import java.util.ArrayList;

public class Score {

    private ArrayList<FrameState> frames;

    public Score() {
        frames = new ArrayList<>();
        FrameState firstFrame = new NewFrame();
        frames.add(firstFrame);
    }

    public void roll(int... rolls) {
        for (int roll : rolls) {
            roll(roll);
        }
    }

    public boolean isLastFrame () {
        return frames.size() == 10;
    }

    public void roll(int roll) {
        if (roll < 0 || roll >10) throw new PinsOutOfBoundsException();

        currentFrame().roll(this, roll);

        if (frames.size()  >= 3) {
            frames.get(frames.size() - 3).roll(this, roll);
        }

        if (frames.size() >= 2) {
            frames.get(frames.size() - 2).roll(this, roll);
        }

        if (currentFrame().isRolled() && !isLastFrame()) {
            frames.add(new NewFrame());
        }
    }

    public String print() {
        StringBuilder print = new StringBuilder();
        for (FrameState frame : frames) {
            if (!print.toString().isEmpty()) print.append(" ");
            print.append(frame.print());
        }
        return print.toString();
    }

    public int getScore() {
        int score = 0;
        for (FrameState frame : frames) {
            score += frame.score();
        }
        return score;
    }

    public void setCurrentFrame(FrameState currentFrame) {
        int currentFrameIndex = frames.size() - 1;
        frames.set(currentFrameIndex, currentFrame);
    }

    public FrameState currentFrame() {
        return frames.get(frames.size() - 1);
    }

}
