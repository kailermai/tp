package seedu.address.model.record;

/**
 * Represents a record of student scores over a period of weeks.
 */
public class Record {

    private AttendanceScore attendanceScore;
    private SubmissionScore submissionScore;
    private ParticipationScore participationScore;

    /**
     * Constructs a {@code Record} object with the specified scores.
     *
     * @param attendanceScore The attendance score representing the student's attendance record.
     * @param submissionScore The submission score representing the student's assignment submission record.
     * @param participationScore The participation score representing the student's class participation record.
     */
    public Record(AttendanceScore attendanceScore, SubmissionScore submissionScore,
                  ParticipationScore participationScore) {
        this.attendanceScore = attendanceScore;
        this.submissionScore = submissionScore;
        this.participationScore = participationScore;
    }

    public AttendanceScore getAttendanceScore() {
        return this.attendanceScore;
    }

    public SubmissionScore getSubmissionScore() {
        return this.submissionScore;
    }

    public ParticipationScore getParticipationScore() {
        return this.participationScore;
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
