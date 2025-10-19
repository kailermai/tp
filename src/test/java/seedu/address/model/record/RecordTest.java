package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_AMY;

import org.junit.jupiter.api.Test;

public class RecordTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Record(null, null,
                null));
    }

    @Test
    public void equals() {
        Record record = new Record(new AttendanceScore(AttendanceScore.MIN_SCORE),
                new SubmissionScore(SubmissionScore.MIN_SCORE), new ParticipationScore(ParticipationScore.MAX_SCORE));

        // same object -> returns true
        assertTrue(record.equals(record));

        // same values -> returns true
        Record recordCopy = new Record(new AttendanceScore(record.getAttendanceScore()),
                new SubmissionScore(record.getSubmissionScore()),
                new ParticipationScore(record.getParticipationScore()));
        assertTrue(record.equals(recordCopy));

        // different values -> returns false
        Record differentAttendanceRecord = new Record(new AttendanceScore(AttendanceScore.MAX_SCORE),
                new SubmissionScore(SubmissionScore.MIN_SCORE), new ParticipationScore(ParticipationScore.MAX_SCORE));
        assertFalse(record.equals(differentAttendanceRecord));

        Record differentSubmissionRecord = new Record(new AttendanceScore(AttendanceScore.MIN_SCORE),
                new SubmissionScore(SubmissionScore.MAX_SCORE), new ParticipationScore(ParticipationScore.MAX_SCORE));
        assertFalse(record.equals(differentSubmissionRecord));

        Record differentParticipationRecord = new Record(new AttendanceScore(AttendanceScore.MIN_SCORE),
                new SubmissionScore(SubmissionScore.MIN_SCORE), new ParticipationScore(ParticipationScore.MIN_SCORE));
        assertFalse(record.equals(differentParticipationRecord));

        // different types -> returns false
        assertFalse(record.equals(1));

        // null -> returns false
        assertFalse(record.equals(null));

        // different record -> returns false
        assertFalse(record.equals(VALID_RECORD_AMY));
    }

    @Test
    public void hashCodeTest() {
        // same record -> returns same hashcode
        Record r1 = new Record(new AttendanceScore(AttendanceScore.MIN_SCORE),
                new SubmissionScore(SubmissionScore.MIN_SCORE), new ParticipationScore(ParticipationScore.MIN_SCORE));
        Record r2 = new Record(new AttendanceScore(AttendanceScore.MIN_SCORE),
                new SubmissionScore(SubmissionScore.MIN_SCORE), new ParticipationScore(ParticipationScore.MIN_SCORE));
        assertEquals(r1.hashCode(), r2.hashCode());

        // different record -> returns different hashcode
        Record r3 = new Record(new AttendanceScore(AttendanceScore.MAX_SCORE),
                new SubmissionScore(SubmissionScore.MAX_SCORE), new ParticipationScore(ParticipationScore.MAX_SCORE));
        assertNotEquals(r1.hashCode(), r3.hashCode());
    }
}
