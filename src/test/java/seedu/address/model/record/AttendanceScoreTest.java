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

    @Test
    public void equals() {
        AttendanceScore attendanceScore = new AttendanceScore(AttendanceScore.MIN_SCORE);

        // same object -> returns true
        assertTrue(attendanceScore.equals(attendanceScore));

        // same values -> returns true
        assertTrue(attendanceScore.equals(new AttendanceScore(AttendanceScore.MIN_SCORE)));

        // different types -> returns false
        assertFalse(attendanceScore.equals(5));

        // null -> returns false
        assertFalse(attendanceScore.equals(null));

        // different attendance score -> returns false
        assertFalse(attendanceScore.equals(new AttendanceScore(AttendanceScore.MAX_SCORE)));
    }
}
