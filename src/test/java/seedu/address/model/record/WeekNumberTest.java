package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WeekNumberTest {

    @Test
    public void isValidWeekNumber_integerWithinRange_returnsTrue() {
        assertTrue(WeekNumber.isValidWeekNumber(WeekNumber.MIN_WEEK_NUMBER));
        assertTrue(WeekNumber.isValidWeekNumber(WeekNumber.MAX_WEEK_NUMBER));

        int midWeek = (WeekNumber.MAX_WEEK_NUMBER - WeekNumber.MIN_WEEK_NUMBER) / 2;
        assertTrue(WeekNumber.isValidWeekNumber(midWeek));
    }

    @Test
    public void isValidWeekNumber_integerOutOfRange_returnsFalse() {
        assertFalse(WeekNumber.isValidWeekNumber(WeekNumber.MIN_WEEK_NUMBER - 1));
        assertFalse(WeekNumber.isValidWeekNumber(WeekNumber.MAX_WEEK_NUMBER + 1));
    }

    @Test
    public void equals() {
        WeekNumber weekNumber = new WeekNumber(WeekNumber.MIN_WEEK_NUMBER);

        // same object -> returns true
        assertTrue(weekNumber.equals(weekNumber));

        // same values -> returns true
        assertTrue(weekNumber.equals(new WeekNumber(WeekNumber.MIN_WEEK_NUMBER)));

        // different types -> returns false
        assertFalse(weekNumber.equals(5));

        // null -> returns false
        assertFalse(weekNumber.equals(null));

        // different week number -> returns false
        assertFalse(weekNumber.equals(new WeekNumber(WeekNumber.MAX_WEEK_NUMBER)));
    }
}
