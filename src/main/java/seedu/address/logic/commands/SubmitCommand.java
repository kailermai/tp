package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.record.Record;
import seedu.address.model.record.SubmissionRecord;
import seedu.address.model.record.SubmissionScore;
import seedu.address.model.record.WeekNumber;
import seedu.address.model.recordlist.RecordList;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK_NUMBER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

public class SubmitCommand extends Command{
    public static final String COMMAND_WORD = "sub";

    public static final String MESSAGE_USE = COMMAND_WORD + ": Records the submission of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_WEEK_NUMBER + "WEEK NUMBER] "
            + "[" + PREFIX_SCORE + "ATTENDANCE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_WEEK_NUMBER + "3 "
            + PREFIX_SCORE + "0";

    public static final String MESSAGE_STUDENT_SUBMISSION_RECORDED_SUCCESS = "Submission recorded for student: %1$s";
    public static final String MESSAGE_INVALID_INDEX = "Invalid index. Only positive integers are allowed.";


    private final Index targetIndex;
    private final WeekNumber weekNumber;
    private final SubmissionRecord submissionRecord;

    public SubmitCommand(Index targetIndex, WeekNumber weekNumber, SubmissionRecord submissionRecord) {
        requireNonNull(targetIndex);
        requireNonNull(weekNumber);
        requireNonNull(submissionRecord);

        this.targetIndex = targetIndex;
        this.weekNumber = weekNumber;
        this.submissionRecord = submissionRecord;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (!isValidIndex(targetIndex)) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student targetStudent = lastShownList.get(targetIndex.getZeroBased());
        Student editedStudent = createEditedStudent(targetStudent, weekNumber, submissionRecord);
        model.setStudent(targetStudent, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_STUDENT_SUBMISSION_RECORDED_SUCCESS, Messages.format(editedStudent)));



    }


    public static boolean isValidIndex(Index index) {
        int num = index.getOneBased();
        return num > 0;
    }

    private static Student createEditedStudent(Student targetStudent, WeekNumber weekNumber, SubmissionRecord submissionRecord ) {
        Name name = targetStudent.getName();
        Phone phone = targetStudent.getPhone();
        Email email = targetStudent.getEmail();
        Address address = targetStudent.getAddress();
        Set<Tag> tags = targetStudent.getTags();
        RecordList recordList = targetStudent.getRecordList();
        ArrayList<SubmissionRecord> submissionRecords = recordList.getSubmissionRecords();
        submissionRecords.add(weekNumber.getWeekNumber() - 1, submissionRecord);

        return new Student(name, phone, email, address, tags, recordList);
    }

}
