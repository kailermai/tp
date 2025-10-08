package seedu.address.model.record;

/**
 * Represents a student's submission record for weekly assessments.
 */
public class Submission extends Record {
    /**
     * Constructs a {@code Submission} instance with the specified maximum score.
     * @param maxScore The maximum score that can be recorded for any week.
     */
    public Submission(int maxScore) {
        super(maxScore);
    }

}
