package com.moniqq.train.tdd.bowling;

import com.moniqq.train.tdd.bowling.exceptions.PinsOutOfBoundsException;
import com.moniqq.train.tdd.bowling.frames.FrameInProgress;
import com.moniqq.train.tdd.bowling.frames.NewFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ScoreTest {

    @Test
    public void testFirstRoll_OnePin() {
        Score score = new Score();
        score.roll(1);
        assertThat(score.getScore()).isEqualTo(1);
        assertThat(score.print()).isEqualTo("1");
    }

    @Test
    public void testFirstRoll_SevenPins() {
        Score score = new Score();
        score.roll(7);
        assertThat(score.getScore()).isEqualTo(7);
        assertThat(score.print()).isEqualTo("7");
    }

    @Test
    public void testFirstFrame_SevenPins() {
        Score score = new Score();
        score.roll(3);
        score.roll(4);
        assertThat(score.getScore()).isEqualTo(7);
        assertThat(score.print()).isEqualTo("3 4 | ");
    }

    @Test
    public void testSpareFrame() {
        Score score = new Score();
        score.roll(3);
        score.roll(7);
        assertThat(score.print()).isEqualTo("3 / | ");
        assertThat(score.getScore()).isEqualTo(3);
    }

    @Test
    public void testToManyPins_inRoll() {
        assertThatExceptionOfType(PinsOutOfBoundsException.class)
                .isThrownBy(() -> {
                    Score score = new Score();
                    score.roll(12);
                });
    }

    @Test
    public void testToManyPins_inFrame() {
        assertThatExceptionOfType(PinsOutOfBoundsException.class)
                .isThrownBy(() -> {
                    Score score = new Score();
                    score.roll(5);
                    score.roll(7);
                });
    }

    @Test
    public void testSpare_withExtra() {
        Score score = new Score();
        score.roll(3);
        score.roll(7);
        score.roll(7);
        assertThat(score.print()).isEqualTo("3 / | 7");
        assertThat(score.getScore()).isEqualTo(24);
        assertThat(score.currentFrame()).isInstanceOf(FrameInProgress.class);
    }

    @Test
    public void testTwoSpares() {
        Score score = new Score();
        score.roll(4);
        score.roll(6);
        score.roll(8);
        score.roll(2);
        assertThat(score.print()).isEqualTo("4 / | 8 / | ");
        assertThat(score.getScore()).isEqualTo(10 + 8 + 8);
        assertThat(score.currentFrame()).isInstanceOf(NewFrame.class);
    }

    @Test
    public void testTwoSpares_AndRoll() {
        Score score = new Score();
        score.roll(4);
        score.roll(6);
        score.roll(8);
        score.roll(2);
        score.roll(7);
        assertThat(score.print()).isEqualTo("4 / | 8 / | 7");
        assertThat(score.getScore()).isEqualTo((10 + 8) + (10 + 7) + 7);
    }

    @Test
    public void testOneSpares_AndRoll() {
        Score score = new Score();
        score.roll(4);
        score.roll(6);
        score.roll(8);
        score.roll(1);
        assertThat(score.print()).isEqualTo("4 / | 8 1 | ");
        assertThat(score.getScore()).isEqualTo((10 + 8) + 9);
    }

    @Test
    public void testStrike() {
        Score score = new Score();
        score.roll(10);
        assertThat(score.print()).isEqualTo("X | ");
        assertThat(score.getScore()).isEqualTo(0);
    }

    @Test
    public void testStrikeStrikeStrike() {
        Score score = new Score();
        score.roll(10);
        score.roll(10);
        score.roll(10);
        assertThat(score.print()).isEqualTo("X | X | X | ");
        assertThat(score.getScore()).isEqualTo(30);
    }

    @Test
    public void testStrikeStrikeStrike7() {
        Score score = new Score();
        score.roll(10);
        score.roll(10);
        score.roll(10);
        score.roll(7);
        assertThat(score.print()).isEqualTo("X | X | X | 7");
        assertThat(score.getScore()).isEqualTo(64);
    }

    @Test
    public void testFullGame_endsWithStrike() {
        Score score = new Score();

        score.roll(8, 2);
        score.roll(7, 3);
        score.roll(7, 2);
        score.roll(10);
        score.roll(10);
        score.roll(8, 2);
        score.roll(7, 3);
        score.roll(7, 2);
        score.roll(10);
        score.roll(10, 2, 3);

        assertThat(score.print()).isEqualTo("8 / | 7 / | 7 2 | X | X | 8 / | 7 / | 7 2 | X | X 2 3 |");
        assertThat(score.getScore()).isEqualTo(17 + 17 + 9 + 28 + 20 + 17 + 17 + 9 + 22 + 15);
    }

    @Test
    public void testFullGame_endsWithSpare() {
        Score score = new Score();

        score.roll(8, 2);
        score.roll(7, 3);
        score.roll(7, 2);
        score.roll(10);
        score.roll(10);
        score.roll(8, 2);
        score.roll(7, 3);
        score.roll(7, 2);
        score.roll(10);
        score.roll(8, 2, 3);

        assertThat(score.print()).isEqualTo("8 / | 7 / | 7 2 | X | X | 8 / | 7 / | 7 2 | X | 8 / 3 |");
        assertThat(score.getScore()).isEqualTo(17 + 17 + 9 + 28 + 20 + 17 + 17 + 9 + 20 + 13);
    }

}
