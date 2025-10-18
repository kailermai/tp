package seedu.address.model.record;

/**
 * Represents an abstract concept of a score used in various student records.
 */
public abstract class Score {
    public static final int MIN_SCORE = 0;

    protected final int value;

    public Score(int value) {
        this.value = value;
    }

    public int getScore() {
        return value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
