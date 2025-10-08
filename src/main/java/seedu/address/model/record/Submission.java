package seedu.address.model.record;

import seedu.address.commons.core.index.Index;

/**
 * Class for student's submission record
 */
public class Submission extends Record{

    /**
     * Constructor for submission class
     * @param MaxScore The maximum score that can be recorded for any week.
     *                  This value is used to initialize the maximum allowed score for the submission record.
     */
    public Submission (int MaxScore) {
        super(MaxScore);
    }

    public int getScore(Index index) {
        return super.getScore(index);
    }

    public void setScore(Index index, int score) {
        super.setScore(index, score);
    }

    public int getMaxScore() {
        return super.getMaxScore();
    }
}
