package seedu.address.model.record;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class SubmissionScore extends Record{
    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 1;
    public static final String MESSAGE_CONSTRAINTS = "Only values from "
            + MIN_SCORE + " to " + MAX_SCORE + " are allowed.";

    public final int score;

    public SubmissionScore(int submissionScore) {
        this.score = submissionScore;
        checkArgument(isValidSubmissionScore(score), MESSAGE_CONSTRAINTS);
    }

    public static boolean isValidSubmissionScore(int score) {
        return score >= MIN_SCORE && score <= MAX_SCORE;
    }
}
