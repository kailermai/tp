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
}
