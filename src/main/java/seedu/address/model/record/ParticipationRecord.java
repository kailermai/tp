package seedu.address.model.record;

import seedu.address.commons.exceptions.IllegalValueException;

public class ParticipationRecord {

    static final int MAX_SCORE = 5;
    private int score;

    /**
     * Constructs a Participation object with the given score and remarks.
     *
     * @param score The participation score (0 to MAX_SCORE).
     * @param remarks Additional remarks about the participation.
     * @throws IllegalArgumentException if the score is out of range.
     */
    public ParticipationRecord(int score, String remarks) throws IllegalValueException {
        if (score < 0 || score > MAX_SCORE) {
            throw new IllegalValueException("Score must be between 0 and " + MAX_SCORE);
        }
        this.score = score;
    }

    public int getScore() {
        return score;
    }


    
    @Override
    public String toString() {
        return "Score: " + score;
    }


}
