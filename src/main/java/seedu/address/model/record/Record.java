package seedu.address.model.record;

import seedu.address.commons.core.index.Index;

/**
 * Represents an abstract student record over a period of weeks.
 */
public abstract class Record {
    private final int[] weeks;
    private final int maxScore;

    /**
     * Constructs a new {@code Record} object with the specified maximum score.
     *
     * @param maxScore The maximum score that can be recorded for any week.
     *                 This value is used to initialize the maximum allowed score for the record.
     */
    public Record(int maxScore) {
        this.weeks = new int[13];
        this.maxScore = maxScore;
    }

    public int getMaxScore() {
        return this.maxScore;
    }

    public int getScore(Index index) {
        return this.weeks[index.getZeroBased()];
    }

    public void setScore(Index index, int score) {
        this.weeks[index.getZeroBased()] = score;
    }
}
