package seedu.address.model.record;

import seedu.address.commons.core.index.Index;

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

    /**
     * Returns the maximum score that can be recorded for any week.
     *
     * @return the maximum possible score
     */
    @Override
    public int getMaxScore() {
        return super.getMaxScore();
    }

    /**
     * Returns the submission score for the specified week.
     *
     * @param index the week index for which the score is retrieved
     * @return the submission score for that week
     */
    @Override
    public int getScore(Index index) {
        return super.getScore(index);
    }

    /**
     * Updates the submission score for the specified week.
     *
     * @param index the week index whose score is to be updated
     * @param score the new score to record for that week
     */
    @Override
    public void setScore(Index index, int score) {
        super.setScore(index, score);
    }
}
