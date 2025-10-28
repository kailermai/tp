package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    public void equals() {

        AttendanceScore attendanceScore = new AttendanceScore(AttendanceScore.MAX_SCORE);
        SubmissionScore submissionScore = new SubmissionScore(SubmissionScore.MAX_SCORE);
        ParticipationScore participationScore = new ParticipationScore(ParticipationScore.MAX_SCORE);

        // same object -> equal
        assertEquals(attendanceScore, attendanceScore);
        assertEquals(participationScore, participationScore);

        // same type and value -> equal
        assertEquals(new SubmissionScore(SubmissionScore.MAX_SCORE), submissionScore);

        // different types -> not equal
        assertNotEquals(attendanceScore, submissionScore);
        assertNotEquals(submissionScore, participationScore);
        assertNotEquals(participationScore, attendanceScore);

        // null -> not equal
        assertNotEquals(attendanceScore, null);

        // same type and different score -> not equal
        assertNotEquals(new ParticipationScore(ParticipationScore.MIN_SCORE), participationScore);
    }
}

