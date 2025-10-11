package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_LIST_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.record.WeekNumber;
import seedu.address.model.recordlist.RecordList;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class RecordCommandTest {

    private static final WeekNumber WEEK_NUMBER_STUB = new WeekNumber(WeekNumber.MIN_WEEK_NUMBER);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRecordUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withRecordList(new RecordList()).build();
        model.setStudent(firstStudent, editedStudent);

        RecordCommand recordCommand = new RecordCommand(INDEX_FIRST_STUDENT, WEEK_NUMBER_STUB, VALID_RECORD_AMY);

        String expectedMessage = String.format(
                RecordCommand.MESSAGE_ADD_RECORD_SUCCESS, Messages.format(editedStudent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(recordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addRecordFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased())).withRecordList(new RecordList()).build();
        model.setStudent(firstStudent, editedStudent);

        RecordCommand recordCommand = new RecordCommand(INDEX_FIRST_STUDENT, WEEK_NUMBER_STUB, VALID_RECORD_AMY);

        String expectedMessage = String.format(
                RecordCommand.MESSAGE_ADD_RECORD_SUCCESS, Messages.format(editedStudent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(recordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        RecordCommand recordCommand = new RecordCommand(outOfBoundIndex, WEEK_NUMBER_STUB, VALID_RECORD_AMY);

        assertCommandFailure(recordCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;

        // ensures that outOfBoundsIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        RecordCommand recordCommand = new RecordCommand(outOfBoundIndex, WEEK_NUMBER_STUB, VALID_RECORD_AMY);

        assertCommandFailure(recordCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_editRecordUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withRecordList(VALID_RECORD_LIST_AMY).build();
        model.setStudent(firstStudent, editedStudent);

        RecordCommand recordCommand = new RecordCommand(INDEX_FIRST_STUDENT, WEEK_NUMBER_STUB, VALID_RECORD_BOB);

        String expectedMessage = String.format(
                RecordCommand.MESSAGE_UPDATE_RECORDED_SUCCESS, Messages.format(editedStudent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(recordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editRecordFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased())).withRecordList(VALID_RECORD_LIST_AMY).build();
        model.setStudent(firstStudent, editedStudent);

        RecordCommand recordCommand = new RecordCommand(INDEX_FIRST_STUDENT, WEEK_NUMBER_STUB, VALID_RECORD_BOB);

        String expectedMessage = String.format(
                RecordCommand.MESSAGE_UPDATE_RECORDED_SUCCESS, Messages.format(editedStudent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(recordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final RecordCommand standardCommand = new RecordCommand(
                INDEX_FIRST_STUDENT, new WeekNumber(WeekNumber.MIN_WEEK_NUMBER), VALID_RECORD_AMY);

        // same values -> returns true
        RecordCommand commandWithSameValues = new RecordCommand(
                INDEX_FIRST_STUDENT, new WeekNumber(WeekNumber.MIN_WEEK_NUMBER), VALID_RECORD_AMY);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new RecordCommand(INDEX_SECOND_STUDENT, new WeekNumber(WeekNumber.MIN_WEEK_NUMBER), VALID_RECORD_AMY)));

        // different week number -> returns false
        assertFalse(standardCommand.equals(
                new RecordCommand(INDEX_FIRST_STUDENT, new WeekNumber(WeekNumber.MAX_WEEK_NUMBER), VALID_RECORD_AMY)));

        // different record -> returns false
        assertFalse(standardCommand.equals(
                new RecordCommand(INDEX_FIRST_STUDENT, new WeekNumber(WeekNumber.MIN_WEEK_NUMBER), VALID_RECORD_BOB)));
    }
}
