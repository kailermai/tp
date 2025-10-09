package seedu.address.model.record;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a submission score for a submission record.
 * A valid week number is a positive integer between {@value MIN_SCORE} and {@value MAX_SCORE}, inclusive.
 */
public class SubmissionScore extends Score {
    public static final int MAX_SCORE = 1;
    public static final String MESSAGE_CONSTRAINTS = "Only values from "
            + MIN_SCORE + " to " + MAX_SCORE + " are allowed.";

    public final int score;

    /**
     * Constructs an {@code SubmissionScore} object with the given score.
     *
     * @param submissionScore The score to be assigned.
     * @throws IllegalArgumentException If the provided week number is not within the valid range.
     */
    public SubmissionScore(int submissionScore) {
        this.score = submissionScore;
        checkArgument(isValidSubmissionScore(score), MESSAGE_CONSTRAINTS);
    }

    public static boolean isValidSubmissionScore(int score) {
        return score >= MIN_SCORE && score <= MAX_SCORE;
    }
}
