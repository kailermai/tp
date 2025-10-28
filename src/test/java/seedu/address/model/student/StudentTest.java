package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // same name, all other attributes different -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).withStudentNumber(VALID_STUDENT_NUMBER_BOB)
                .withTelegram(VALID_TELEGRAM_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same student number, all other attributes different -> returns true
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND).withTelegram(VALID_TELEGRAM_BOB).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // different student number, all other attributes same -> returns false
        editedAlice = new StudentBuilder(ALICE).withStudentNumber(VALID_STUDENT_NUMBER_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // student number differs in case, all other attributes same -> returns true
        Student editedAmy = new StudentBuilder(AMY).withStudentNumber(VALID_STUDENT_NUMBER_AMY.toLowerCase()).build();
        assertTrue(AMY.isSameStudent(editedAmy));

    }

    @Test
    public void hasNonStandardName() {
        Name standardName = new Name("test");
        Name nonStandardName = new Name("John-Doe");

        Student studentWithStandardName = new StudentBuilder().withName(standardName.toString()).build();
        Student studentWithNonStandardName = new StudentBuilder().withName(nonStandardName.toString()).build();

        assertFalse(studentWithStandardName.getHasNonStandardName());
        assertTrue(studentWithNonStandardName.getHasNonStandardName());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different student -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different student number -> returns false
        editedAlice = new StudentBuilder(ALICE).withStudentNumber(VALID_STUDENT_NUMBER_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different telegram -> returns false
        editedAlice = new StudentBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Student.class.getCanonicalName() + "{name=" + ALICE.getName()
                + ", studentNumber=" + ALICE.getStudentNumber()
                + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail()
                + ", telegram=" + ALICE.getTelegram()
                + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
