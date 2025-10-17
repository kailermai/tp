package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_AMY;

import org.junit.jupiter.api.Test;

public class RecordTest {

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

        // different types -> returns false
        assertFalse(record.equals(1));

        // null -> returns false
        assertFalse(record.equals(null));

        // different record -> returns false
        assertFalse(record.equals(VALID_RECORD_AMY));
    }
}
