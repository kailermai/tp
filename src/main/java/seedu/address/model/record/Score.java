package seedu.address.model.record;

/**
 * Represents an abstract concept of a score used in various student records.
 */
public abstract class Score {
    public static final int MIN_SCORE = 0;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public int getScore() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (this.getClass() != other.getClass()) {
            return false;
        }

        Score otherScore = (Score) other;
        return this.value == otherScore.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
