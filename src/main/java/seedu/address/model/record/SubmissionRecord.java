package seedu.address.model.record;

/**
 * Represents a student's submission record for weekly assessments.
 */
public class SubmissionRecord {
    private final SubmissionScore score;

    public SubmissionRecord(SubmissionScore score) {
        this.score = score;
    }

    public SubmissionScore getScore() {
        return this.score;
    }

    @Override
    public String toString() {
        return "Score: " + this.getScore();
    }

}
