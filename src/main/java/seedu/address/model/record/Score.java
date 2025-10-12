package seedu.address.model.record;

/**
 * Represents an abstract concept of a score used in various student records.
 */
public abstract class Score {
    public static final int MIN_SCORE = 0;

    public final int value;

    protected Score(int value) {
        this.value = value;
    }

    public int getScore() {
        return value;
    }

}
