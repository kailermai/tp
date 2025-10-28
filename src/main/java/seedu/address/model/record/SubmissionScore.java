package seedu.address.model.record;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a submission score in a student record.
 * A valid submission score is a positive integer between {@value MIN_SCORE} and {@value MAX_SCORE}, inclusive.
 */
public class SubmissionScore extends Score {
    public static final int MAX_SCORE = 1;
    public static final String MESSAGE_CONSTRAINTS = "Submission scores can only be integers between "
            + MIN_SCORE + " to " + MAX_SCORE + " inclusive.";

    /**
     * Constructs an {@code SubmissionScore} object with the given score.
     *
     * @param submissionScore The score to be assigned.
     * @throws IllegalArgumentException If the provided submission score is not within the valid range.
     */
    public SubmissionScore(int submissionScore) throws IllegalArgumentException {
        super(submissionScore);
        checkArgument(isValidSubmissionScore(submissionScore), MESSAGE_CONSTRAINTS);
    }

    public static boolean isValidSubmissionScore(int score) {
        return score >= MIN_SCORE && score <= MAX_SCORE;
    }
}
