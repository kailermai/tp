package seedu.address.model.record;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a week number in a typical semester.
 * A valid week number is a positive integer between {@value MIN_WEEK_NUMBER} and {@value MAX_WEEK_NUMBER}, inclusive.
 */
public class WeekNumber {

    public static final int MIN_WEEK_NUMBER = 1;
    public static final int MAX_WEEK_NUMBER = 13;

    public static final String MESSAGE_CONSTRAINTS = "Week number should be a positive integer within the range "
            + "[" + MIN_WEEK_NUMBER + "," + MAX_WEEK_NUMBER + "].";

    public final int weekNumber;

    /**
     * Constructs a {@code WeekNumber} object with the given week number.
     *
     * @param weekNumber The week number to be assigned.
     * @throws IllegalArgumentException If the provided week number is not within the valid range.
     */
    public WeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
        checkArgument(isValidWeekNumber(weekNumber), MESSAGE_CONSTRAINTS);
    }

    public static boolean isValidWeekNumber(int weekNumber) {
        return weekNumber >= MIN_WEEK_NUMBER && weekNumber <= MAX_WEEK_NUMBER;
    }

    public int getWeekNumber() {
        return this.weekNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof WeekNumber otherWeekNumber)) {
            return false;
        }

        return otherWeekNumber.weekNumber == this.weekNumber;
    }
}
