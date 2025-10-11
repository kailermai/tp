package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD;
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
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.Record;
import seedu.address.model.record.SubmissionScore;
import seedu.address.model.record.WeekNumber;
import seedu.address.model.recordlist.RecordList;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class RecordCommandTest {

    private static final Record RECORD_STUB = new Record(new AttendanceScore(0), new SubmissionScore(0),
            new ParticipationScore(0));
    private static final RecordList EMPTY_RECORD_LIST_STUB = new RecordList();
    private static final RecordList POPULATED_RECORD_LIST_STUB = VALID_RECORD_LIST_AMY;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRecordUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withRecordList(EMPTY_RECORD_LIST_STUB).build();

        RecordCommand recordCommand = new RecordCommand(INDEX_FIRST_STUDENT, new WeekNumber(1), VALID_RECORD);

        String expectedMessage = String.format(RecordCommand.MESSAGE_ADD_RECORD_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(recordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addRecordFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased())).withRecordList(EMPTY_RECORD_LIST_STUB).build();

        RecordCommand recordCommand = new RecordCommand(INDEX_FIRST_STUDENT, new WeekNumber(1), VALID_RECORD);

        String expectedMessage = String.format(RecordCommand.MESSAGE_ADD_RECORD_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(recordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        RecordCommand recordCommand = new RecordCommand(outOfBoundIndex, new WeekNumber(1), VALID_RECORD);

        assertCommandFailure(recordCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;

        // ensures that outOfBoundsIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        RecordCommand recordCommand = new RecordCommand(outOfBoundIndex, new WeekNumber(1), VALID_RECORD);

        assertCommandFailure(recordCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_editRecordUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withRecordList(POPULATED_RECORD_LIST_STUB).build();

        RecordCommand recordCommand = new RecordCommand(INDEX_FIRST_STUDENT, new WeekNumber(1), RECORD_STUB);

        String expectedMessage = String.format(RecordCommand.MESSAGE_UPDATE_RECORDED_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(recordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editRecordFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased())).withRecordList(POPULATED_RECORD_LIST_STUB).build();

        RecordCommand recordCommand = new RecordCommand(INDEX_FIRST_STUDENT, new WeekNumber(1), RECORD_STUB);

        String expectedMessage = String.format(RecordCommand.MESSAGE_UPDATE_RECORDED_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(recordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {

    }
}
