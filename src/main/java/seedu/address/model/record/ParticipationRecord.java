package seedu.address.model.record;


public class ParticipationRecord {

    static final int MAX_SCORE = 5;
    private final ParticipationScore participationScore;

    /**
     * Constructs a Participation object with the given score and remarks.
     *
     * @param score The participation score (0 to MAX_SCORE).
     * @throws IllegalArgumentException if the score is out of range.
     */
    public ParticipationRecord(ParticipationScore score) {
        this.participationScore = score;
    }

    public int getScore() {
        return participationScore.getScore();
    }

    @Override
    public String toString() {
        return "Score: " + participationScore.getScore();
    }


}
