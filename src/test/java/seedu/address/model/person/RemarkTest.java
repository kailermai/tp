package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RemarkTest {
    @Test
    public void equal() {
        Remark remark = new Remark("test");

        // same object => true
        assertTrue(remark.equals(remark));

        // same values -> true
        assertTrue(remark.equals(new Remark("test")));

        // different type -> false
        assertFalse(remark.equals(new Name("123")));

        // different value -> false
        assertFalse(remark.equals(new Remark("123")));

        // null -> false
        assertFalse(remark.equals(null));
    }
}
