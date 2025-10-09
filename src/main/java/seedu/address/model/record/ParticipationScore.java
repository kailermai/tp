package seedu.address.model.record;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a participation score for a participation record.
 * A valid participation score is an integer between {@value MIN_SCORE} and {@value MAX_SCORE}, inclusive.
 */
public class ParticipationScore {
    static final int MIN_SCORE = 0;
    static final int MAX_SCORE = 5;
    static final String MESSAGE_CONSTRAINTS = "Only values from "
            + MIN_SCORE + " to " + MAX_SCORE + " are allowed.";
    private final int score;

    /**
     * Constructs a ParticipationScore object with the given score.
     *
     * @param score The participation score to be assigned.
     */
    public ParticipationScore(int score) {
        this.score = score;
        checkArgument(isValidParticipationScore(score), MESSAGE_CONSTRAINTS);
    }

    public int getScore() {
        return score;
    }

    public static boolean isValidParticipationScore(int score) {
        return score >= MIN_SCORE && score <= MAX_SCORE;
    }
    @Override
    public String toString() {
        return String.valueOf(this.score);
    }
}
