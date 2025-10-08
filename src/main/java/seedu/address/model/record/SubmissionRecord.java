package seedu.address.model.record;

/**
 * Represents a student's submission record for weekly assessments.
 */
public class SubmissionRecord extends Record {
    public static final int MAX_SUBMISSION_SCORE = 5;

    private int score;
    /**
     * Constructs a {@code Submission} instance with the specified maximum score.
     * @param maxScore The maximum score that can be recorded for any week.
     */
    public SubmissionRecord(int maxScore) {
        super(maxScore);
    }

}
