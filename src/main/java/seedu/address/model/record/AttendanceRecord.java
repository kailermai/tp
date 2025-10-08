package seedu.address.model.record;

/**
 * Represents a student's attendance record.
 */
public class AttendanceRecord {

    private int score;
    private String absenceReason;

    /**
     * Constructs an {@code AttendanceRecord} object with the specified score and reason for absence.
     *
     * @param score The attendance score of the student. 1 for present, 0 for absent.
     * @param absenceReason The reason for the student's absence, if applicable. This field is optional.
     */
    public AttendanceRecord(int score, String absenceReason) {
        this.score = score;
        this.absenceReason = absenceReason;
    }

    @Override
    public String toString() {
        return this.score + " " + this.absenceReason;
    }

}
