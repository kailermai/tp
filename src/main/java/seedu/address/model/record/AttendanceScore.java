package seedu.address.model.record;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an attendance score for an attendance record.
 * A valid week number is a positive integer between {@value MIN_SCORE} and {@value MAX_SCORE}, inclusive.
 */
public class AttendanceScore extends Score {
    public static final int MAX_SCORE = 1;
    public static final String MESSAGE_CONSTRAINTS = "Attendance scores can only be integers between "
            + MIN_SCORE + " to " + MAX_SCORE + " inclusive.";

    /**
     * Constructs an {@code AttendanceScore} object with the given score.
     *
     * @param attendanceScore The score to be assigned.
     * @throws IllegalArgumentException If the provided week number is not within the valid range.
     */
    public AttendanceScore(int attendanceScore) {
        super(attendanceScore);
        checkArgument(isValidAttendanceScore(attendanceScore), MESSAGE_CONSTRAINTS);
    }

    public static boolean isValidAttendanceScore(int attendanceScore) {
        return attendanceScore >= MIN_SCORE && attendanceScore <= MAX_SCORE;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AttendanceScore otherScore)) {
            return false;
        }

        return otherScore.value == this.value;
    }
}
