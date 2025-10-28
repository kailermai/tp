package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("+92")); // less than 3 numbers with plus sign
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("+")); // only plus sign

        // valid phone numbers
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("124293842033123")); // long phone numbers
        assertTrue(Phone.isValidPhone("+6512345678")); // with plus sign
        assertTrue(Phone.isValidPhone("123-456-7890")); // with hyphens
        assertTrue(Phone.isValidPhone("+65-1234-5678")); // with plus sign and hyphens

        // valid phone numbers with non-standard characters
        assertTrue(Phone.isValidPhone("9312 1534")); // spaces within digits
        assertTrue(Phone.isValidPhone("++6512345678")); // multiple plus signs
        assertTrue(Phone.isValidPhone("123-456-7890-")); // ends with a hyphen
        assertTrue(Phone.isValidPhone("-9222")); // starts with a hyphen
        assertTrue(Phone.isValidPhone("65+1234")); // plus sign in middle
        assertTrue(Phone.isValidPhone("+-6512345678")); // plus sign followed by hyphen
        assertTrue(Phone.isValidPhone("123.456.7890")); // dots within digits
        assertTrue(Phone.isValidPhone("123 (65) 7890")); // brackets within digits
    }

    @Test
    public void hasNonStandardCharacters() {
        Phone phone1 = new Phone("9999");
        Phone phone2 = new Phone("+999-999");
        Phone phone3 = new Phone("9999-");
        Phone phone4 = new Phone("99 99");
        Phone phone5 = new Phone("999.999");
        Phone phone6 = new Phone("999(999)");

        assertFalse(phone1.hasNonStandardCharacters());
        assertFalse(phone2.hasNonStandardCharacters());

        assertTrue(phone3.hasNonStandardCharacters());
        assertTrue(phone4.hasNonStandardCharacters());
        assertTrue(phone5.hasNonStandardCharacters());
        assertTrue(phone6.hasNonStandardCharacters());
    }

    @Test
    public void equals() {
        Phone phone = new Phone("999");

        // same values -> returns true
        assertTrue(phone.equals(new Phone("999")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new Phone("995")));
    }
}
