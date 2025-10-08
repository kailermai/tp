package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.record.SubmissionRecord;
import seedu.address.model.student.Student;

import java.util.List;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK_NUMBER;

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
    public static final String MESSAGE_INVALID_WEEK_NUMBER = "Invalid week number."
            + "Only positive integers in the range [1, 13] are allowed.";
    public static final String MESSAGE_INVALID_SCORE = "Invalid score."
            + "";

    private final Index targetIndex;
    private final String weekNumber;
    private final String submissionScore;

    public SubmitCommand(Index targetIndex, String weekNumber, String submissionScore) {
        requireNonNull(targetIndex);
        requireNonNull(weekNumber);
        requireNonNull(submissionScore);

        this.targetIndex = targetIndex;
        this.weekNumber = weekNumber;
        this.submissionScore = submissionScore;
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

        if (!isValidWeekNumber(weekNumber)) {
            throw new CommandException(MESSAGE_INVALID_WEEK_NUMBER);
        }

        if (!isValidScore(submissionScore)) {
            throw new CommandException(ME)
        }

    }

    public static boolean isValidWeekNumber(String weekNumber) {
        try {
            int num = Integer.parseInt(weekNumber);
            return num >= 1 && num <= 13;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidScore(String submissionScore) {
        try {
            int num = Integer.parseInt(submissionScore);
            return num >= 0 && num <= SubmissionRecord.
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidIndex(Index index) {
        int num = index.getOneBased();
        return num > 0;
    }

    public static boolean existStudent(Index index) {

    }
}
