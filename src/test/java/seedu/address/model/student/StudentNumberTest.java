package seedu.address.model.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class StudentNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentNumber(null));
    }

    @Test
    public void constructor_invalidStudentNumber_throwsIllegalArgumentException() {
        String invalidStudentNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentNumber(invalidStudentNumber));
    }

    @Test
    public void isValidStudentNumber() {
        // null student number
        assertThrows(NullPointerException.class, () -> StudentNumber.isValidStudentNumber(null));

        // invalid student number
        assertFalse(StudentNumber.isValidStudentNumber("")); // empty string
        assertFalse(StudentNumber.isValidStudentNumber(" ")); // spaces only
        assertFalse(StudentNumber.isValidStudentNumber("ABCDEFGHZ")); // contains only alphabetical characters
        assertFalse(StudentNumber.isValidStudentNumber("123456789")); // contains only numbers
        assertFalse(StudentNumber.isValidStudentNumber("A01234B56")); // wrong format
        assertFalse(StudentNumber.isValidStudentNumber("B0123456Z")); // doesn't start with A

        // valid student number
        assertTrue(StudentNumber.isValidStudentNumber("A0123456Z")); // right format
        assertTrue(StudentNumber.isValidStudentNumber("a0123456z")); // lowercase letters
    }

    @Test
    public void equals() {
        StudentNumber studentNumber = new StudentNumber("A0123456Z");

        // same values -> returns true
        assertTrue(studentNumber.equals(new StudentNumber("A0123456Z")));

        // same object -> returns true
        assertTrue(studentNumber.equals(studentNumber));

        // null -> returns false
        assertFalse(studentNumber.equals(null));

        // different values -> returns false
        assertFalse(studentNumber.equals(new StudentNumber("A0123457Z")));

        // lower case -> returns true;
        assertTrue(studentNumber.equals(new StudentNumber("a0123456z")));
        assertTrue(studentNumber.equals(new StudentNumber("a0123456Z")));
        assertTrue(studentNumber.equals(new StudentNumber("A0123456z")));
    }
}
