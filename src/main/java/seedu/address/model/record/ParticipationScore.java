package seedu.address.model.record;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a participation score in a student record.
 * A valid participation score is an integer between {@value MIN_SCORE} and {@value MAX_SCORE}, inclusive.
 */
public class ParticipationScore extends Score {
    public static final int MAX_SCORE = 5;
    public static final String MESSAGE_CONSTRAINTS = "Participation scores can only be integers between "
            + MIN_SCORE + " to " + MAX_SCORE + " inclusive.";

    /**
     * Constructs a ParticipationScore object with the given score.
     *
     * @param participationScore The participation score to be assigned.
     * @throws IllegalArgumentException If the provided participation score is not within the valid range.
     */
    public ParticipationScore(int participationScore) throws IllegalArgumentException {
        super(participationScore);
        checkArgument(isValidParticipationScore(participationScore), MESSAGE_CONSTRAINTS);
    }

    public static boolean isValidParticipationScore(int score) {
        return score >= MIN_SCORE && score <= MAX_SCORE;
    }
}
