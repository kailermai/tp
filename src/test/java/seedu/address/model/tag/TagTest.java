package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        String longString = "thistagisthirtycharacterslong1";
        String longerString = "thistagisthirty1characterslong1";

        // invalid tag name
        assertFalse(Tag.isValidTagName(""));
        assertFalse(Tag.isValidTagName(" "));
        assertFalse(Tag.isValidTagName(longerString)); // 31 characters long

        assertTrue(Tag.isValidTagName("test")); // alphabets
        assertTrue(Tag.isValidTagName("test123")); // alphanumeric
        assertTrue(Tag.isValidTagName("12345")); // numbers only
        assertTrue(Tag.isValidTagName("a")); // one character
        assertTrue(Tag.isValidTagName(longString)); // 30 characters long
        assertTrue(Tag.isValidTagName("Contains +"));
        assertTrue(Tag.isValidTagName("Contains #"));
    }

}
