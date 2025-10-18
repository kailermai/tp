package seedu.address.model.record;

import static java.util.Objects.requireNonNull;

/**
 * Represents a record of student scores over a period of weeks.
 */
public class Record {

    private final AttendanceScore attendanceScore;
    private final SubmissionScore submissionScore;
    private final ParticipationScore participationScore;

    /**
     * Constructs a {@code Record} object with the specified scores.
     *
     * @param attendanceScore The attendance score representing the student's attendance record.
     * @param submissionScore The submission score representing the student's assignment submission record.
     * @param participationScore The participation score representing the student's class participation record.
     */
    public Record(AttendanceScore attendanceScore, SubmissionScore submissionScore,
                  ParticipationScore participationScore) {
        requireNonNull(attendanceScore);
        requireNonNull(submissionScore);
        requireNonNull(participationScore);
        this.attendanceScore = attendanceScore;
        this.submissionScore = submissionScore;
        this.participationScore = participationScore;
    }

    public int getAttendanceScore() {
        return this.attendanceScore.getScore();
    }

    public int getSubmissionScore() {
        return this.submissionScore.getScore();
    }

    public int getParticipationScore() {
        return this.participationScore.getScore();
    }

    @Override
    public String toString() {
        return String.format("Attendance: %s, Submission: %s, Participation: %s",
                attendanceScore, submissionScore, participationScore);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Record
                && attendanceScore.equals(((Record) other).attendanceScore)
                && submissionScore.equals(((Record) other).submissionScore)
                && participationScore.equals(((Record) other).participationScore));
    }

    @Override
    public int hashCode() {
        return attendanceScore.hashCode() + submissionScore.hashCode() + participationScore.hashCode();
    }
}
