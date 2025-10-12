package seedu.address.model.record;

/**
 * Represents a record of student scores over a period of weeks.
 */
public class Record {

    public static final String MESSAGE_CONSTRAINTS = AttendanceScore.MESSAGE_CONSTRAINTS + "\n"
            + SubmissionScore.MESSAGE_CONSTRAINTS + "\n"
            + ParticipationScore.MESSAGE_CONSTRAINTS;

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
        this.attendanceScore = attendanceScore;
        this.submissionScore = submissionScore;
        this.participationScore = participationScore;
    }

    /**
     * Returns true if the given attendance, submission and participation scores are valid.
     */
    public static boolean isValidRecord(Record record) {
        return AttendanceScore.isValidAttendanceScore(record.attendanceScore.getScore())
                && SubmissionScore.isValidSubmissionScore(record.submissionScore.getScore())
                && ParticipationScore.isValidParticipationScore(record.participationScore.getScore());
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
