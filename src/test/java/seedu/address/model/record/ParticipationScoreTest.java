package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParticipationScoreTest {
    @Test
    public void isValidParticipationScore_integerWithinRange_returnsTrue() {
        assertTrue(ParticipationScore.isValidParticipationScore(ParticipationScore.MIN_SCORE));
        assertTrue(ParticipationScore.isValidParticipationScore(ParticipationScore.MAX_SCORE));
    }

    @Test
    public void isValidParticipationScore_integerOutOfRange_returnsFalse() {
        assertFalse(ParticipationScore.isValidParticipationScore(ParticipationScore.MIN_SCORE - 1));
        assertFalse(ParticipationScore.isValidParticipationScore(ParticipationScore.MAX_SCORE + 1));
    }

    @Test
    public void equals() {
        ParticipationScore participationScore = new ParticipationScore(ParticipationScore.MIN_SCORE);

        // same object -> returns true
        assertTrue(participationScore.equals(participationScore));

        // same values -> returns true
        assertTrue(participationScore.equals(new ParticipationScore(ParticipationScore.MIN_SCORE)));

        // different types -> returns false
        assertFalse(participationScore.equals(5));

        // null -> returns false
        assertFalse(participationScore.equals(null));

        // different participation score -> returns false
        assertFalse(participationScore.equals(new ParticipationScore(ParticipationScore.MAX_SCORE)));
    }
}
