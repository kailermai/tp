package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegram = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));
    }

    @Test
    public void isValidTelegram() {
        String tooLongTelegram = "thisTelegramHandleIsWayTooLongAndShouldDefinitelyNotBeAcceptedByTheValidationCheck"
                + "BecauseItExceedsTheMaximumLengthOfOneHundredCharacters";
        String hundredCharacterTelegram = "A".repeat(100);
        String hundredAndOneCharacterTelegram = "A".repeat(101);

        // null telegram
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid telegram
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("@")); // only non-alphanumeric characters
        assertFalse(Telegram.isValidTelegram("peter*")); // contains non-alphanumeric characters
        assertFalse(Telegram.isValidTelegram(tooLongTelegram)); // telegram is too long
        assertFalse(Telegram.isValidTelegram(hundredAndOneCharacterTelegram)); // telegram is exactly 101 characters

        // valid telegram
        assertTrue(Telegram.isValidTelegram("peterjack")); // alphabets only
        assertTrue(Telegram.isValidTelegram("12345")); // numbers only
        assertTrue(Telegram.isValidTelegram("peter_the_2nd")); //alphaunmeric with underscores
        assertTrue(Telegram.isValidTelegram("Peter_2_")); // with capital letters
        assertTrue(Telegram.isValidTelegram("_____")); // underscores only
        assertTrue(Telegram.isValidTelegram("peterjack123")); // alphanumeric characters
        assertTrue(Telegram.isValidTelegram(hundredCharacterTelegram)); // telegram is 100 characters
        assertTrue(Telegram.isValidTelegram("A")); // one character telegram
    }

    @Test
    public void equals() {
        Telegram telegram = new Telegram("validTelegram");

        // same values -> returns true
        assertTrue(telegram.equals(new Telegram("validTelegram")));

        // same object -> returns true
        assertTrue(telegram.equals(telegram));

        // null -> returns false
        assertFalse(telegram.equals(null));

        // different types -> returns false
        assertFalse(telegram.equals(5.0f));

        // different values -> returns false
        assertFalse(telegram.equals(new Telegram("otherValidTelegram")));
    }
}
