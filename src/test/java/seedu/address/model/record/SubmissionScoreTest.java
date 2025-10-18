package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SubmissionScoreTest {
    @Test
    public void isValidSubmissionScore_integerWithinRange_returnsTrue() {
        assertTrue(SubmissionScore.isValidSubmissionScore(SubmissionScore.MIN_SCORE));
        assertTrue(SubmissionScore.isValidSubmissionScore(SubmissionScore.MAX_SCORE));
    }

    @Test
    public void isValidSubmissionScore_integerOutOfRange_returnsFalse() {
        assertFalse(SubmissionScore.isValidSubmissionScore(SubmissionScore.MIN_SCORE - 1));
        assertFalse(SubmissionScore.isValidSubmissionScore(SubmissionScore.MAX_SCORE + 1));
    }

    @Test
    public void equals() {
        SubmissionScore submissionScore = new SubmissionScore(SubmissionScore.MIN_SCORE);

        // same object -> returns true
        assertTrue(submissionScore.equals(submissionScore));

        // same values -> returns true
        assertTrue(submissionScore.equals(new SubmissionScore(SubmissionScore.MIN_SCORE)));

        // different types -> returns false
        assertFalse(submissionScore.equals(5));

        // null -> returns false
        assertFalse(submissionScore.equals(null));

        // different attendance score -> returns false
        assertFalse(submissionScore.equals(new SubmissionScore(SubmissionScore.MAX_SCORE)));
    }
}
