package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        String tooLongName = "This name is way too long and should definitely not be accepted by the validation check "
                + "because it exceeds the maximum length of one hundred characters which is quite a lot for a name";
        String hundredCharacterName = "A".repeat(100);
        String hundredAndOneCharacterName = "A".repeat(101);

        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName(tooLongName)); // name is too long

        // invalid name, contains special characters
        assertFalse(Name.isValidName(hundredAndOneCharacterName)); // name is exactly 101 characters
        assertFalse(Name.isValidName("Contains@Character"));
        assertFalse(Name.isValidName("Contains#Character"));
        assertFalse(Name.isValidName("Contains$Character"));
        assertFalse(Name.isValidName("Contains!Character"));
        assertFalse(Name.isValidName("Contains%Character"));
        assertFalse(Name.isValidName("Contains^Character"));


        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName(hundredCharacterName)); // name with exactly 100 characters
        assertTrue(Name.isValidName("a")); // one character name

        // valid name, contains allowed special characters
        assertTrue(Name.isValidName("David s/o Jackson")); // name with slash
        assertTrue(Name.isValidName("David s/o Jackson s/o ")); // name with multiple slashes
        assertTrue(Name.isValidName("'O'Brien")); // starts with an apostrophe
        assertTrue(Name.isValidName("-Jean-Luc")); // starts with a hyphen
        assertTrue(Name.isValidName("/Alice")); // starts with a slash
        assertTrue(Name.isValidName("Beyoncé Giselle Knowles-Carter")); // Unicode letter.
        assertTrue(Name.isValidName("María José")); // Unicode letter.
        assertTrue(Name.isValidName("/")); // name is just a slash
        assertTrue(Name.isValidName("contains.character"));
    }

    @Test
    public void hasNonStandardCharacters() {
        Name name1 = new Name("Valid Name");
        Name name2 = new Name("Alice/Bob");
        Name name3 = new Name("Peter 123");
        Name name4 = new Name("Beyoncé Giselle Knowles-Carter");
        Name name5 = new Name("-Jean-Luc");
        Name name6 = new Name("'O'Brien");

        assertFalse(name1.hasNonStandardCharacters());
        assertFalse(name2.hasNonStandardCharacters());
        assertFalse(name3.hasNonStandardCharacters());

        assertTrue(name4.hasNonStandardCharacters());
        assertTrue(name5.hasNonStandardCharacters());
        assertTrue(name6.hasNonStandardCharacters());
    }


    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));
    }
}
