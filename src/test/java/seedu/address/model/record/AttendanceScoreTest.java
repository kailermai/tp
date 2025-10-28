package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AttendanceScoreTest {

    @Test
    public void isValidAttendanceScore_integerWithinRange_returnsTrue() {
        assertTrue(AttendanceScore.isValidAttendanceScore(AttendanceScore.MIN_SCORE));
        assertTrue(AttendanceScore.isValidAttendanceScore(AttendanceScore.MAX_SCORE));
    }

    @Test
    public void isValidAttendanceScore_integerOutOfRange_returnsFalse() {
        assertFalse(AttendanceScore.isValidAttendanceScore(AttendanceScore.MIN_SCORE - 1));
        assertFalse(AttendanceScore.isValidAttendanceScore(AttendanceScore.MAX_SCORE + 1));
    }
}
