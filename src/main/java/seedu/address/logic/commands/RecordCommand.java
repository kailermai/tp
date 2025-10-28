package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBMISSION_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK_NUMBER;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.Record;
import seedu.address.model.record.SubmissionScore;
import seedu.address.model.record.WeekNumber;
import seedu.address.model.recordlist.RecordList;
import seedu.address.model.student.Student;

/**
 * Records the quantifiable data of an existing student in TAHub.
 * This includes attendance, participation, and submission.
 */
public class RecordCommand extends Command {

    public static final String COMMAND_WORD = "record";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add, update or remove a student's weekly record. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_WEEK_NUMBER + "WEEK NUMBER "
            + PREFIX_ATTENDANCE_SCORE + "ATTENDANCE "
            + PREFIX_PARTICIPATION_SCORE + "PARTICIPATION "
            + PREFIX_SUBMISSION_SCORE + "SUBMISSION \n"
            + "Note: Omit all score prefixes to remove the record for the given week.\n"
            + "Example add/update: " + COMMAND_WORD + " 1 "
            + PREFIX_WEEK_NUMBER + WeekNumber.MIN_WEEK_NUMBER + " "
            + PREFIX_ATTENDANCE_SCORE + AttendanceScore.MIN_SCORE + " "
            + PREFIX_PARTICIPATION_SCORE + ParticipationScore.MAX_SCORE + " "
            + PREFIX_SUBMISSION_SCORE + SubmissionScore.MAX_SCORE + " \n"
            + "Example remove: " + COMMAND_WORD + " 1 " + PREFIX_WEEK_NUMBER + "3";

    public static final String MESSAGE_HELP_TITLE = "Create a data record:";
    public static final String MESSAGE_HELP_DESCRIPTION = COMMAND_WORD + " INDEX "
            + PREFIX_WEEK_NUMBER + "WEEK_NUMBER "
            + PREFIX_ATTENDANCE_SCORE + "ATTENDANCE_SCORE "
            + PREFIX_SUBMISSION_SCORE + "SUBMISSION_SCORE "
            + PREFIX_PARTICIPATION_SCORE + "PARTICIPATION_SCORE";

    public static final String MESSAGE_HELP_REMOVE_RECORD_TITLE = "Remove a data record:";
    public static final String MESSAGE_HELP_REMOVE_RECORD_DESCRIPTION = COMMAND_WORD + " INDEX "
            + PREFIX_WEEK_NUMBER + "WEEK_NUMBER";

    public static final String MESSAGE_ADD_RECORD_SUCCESS = "Record added for student: %1$s\n\n"
            + "Record details: %2$s";
    public static final String MESSAGE_UPDATE_RECORDED_SUCCESS = "Record updated for student: %1$s\n\n"
            + "Record details: %2$s";
    public static final String MESSAGE_REMOVE_RECORD_SUCCESS = "Record removed for student: %1$s\n\n";
    public static final String MESSAGE_NO_RECORD_TO_REMOVE = "No existing record found in week %1$s for student %2$s";

    private final Index targetIndex;
    private final WeekNumber weekNumber;
    private final Record record;

    /**
     * @param targetIndex of the student in the filtered student list to record attendance for
     * @param weekNumber the week number of the attendance to be recorded
     * @param record the attendance record to be recorded
     */
    public RecordCommand(Index targetIndex, WeekNumber weekNumber, Record record) {
        requireNonNull(targetIndex);
        requireNonNull(weekNumber);

        this.targetIndex = targetIndex;
        this.weekNumber = weekNumber;
        this.record = record;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX));
        }

        boolean hasExistingRecord;
        Index weekIdx = Index.fromOneBased(weekNumber.getWeekNumber());

        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());

        RecordList studentRecords = studentToEdit.getRecordList();
        hasExistingRecord = studentRecords.getRecord(weekIdx) != null;

        studentRecords.setRecord(weekIdx, record);

        Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getTags(), studentToEdit.getStudentNumber(), studentRecords, studentToEdit.getTelegram());

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent, hasExistingRecord));
    }

    private String generateSuccessMessage(Student studentToEdit, boolean hasExistingRecord) {

        if (record == null) {
            return hasExistingRecord
                    ? String.format(MESSAGE_REMOVE_RECORD_SUCCESS, Messages.format(studentToEdit))
                    : String.format(MESSAGE_NO_RECORD_TO_REMOVE, weekNumber, Messages.format(studentToEdit));
        }

        return hasExistingRecord
                ? String.format(MESSAGE_UPDATE_RECORDED_SUCCESS, Messages.format(studentToEdit), record)
                : String.format(MESSAGE_ADD_RECORD_SUCCESS, Messages.format(studentToEdit), record);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if it is the same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecordCommand)) {
            return false;
        }

        RecordCommand e = (RecordCommand) other;
        if (record == null && e.record == null) {
            return targetIndex.equals(e.targetIndex)
                    && weekNumber.equals(e.weekNumber);
        }

        if (record == null || e.record == null) {
            return false;
        }

        // state check
        return targetIndex.equals(e.targetIndex)
                && weekNumber.equals(e.weekNumber)
                && record.equals(e.record);
    }
}
