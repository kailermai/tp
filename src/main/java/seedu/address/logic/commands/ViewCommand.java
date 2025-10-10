package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

import java.util.List;

/**
 * Shows the trend of a student's performance over weeks.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the trend of a student's performance over weeks.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "View feature is not implemented yet.";

    public static final String MESSAGE_ARGUMENTS = "INDEX: %1$d";

    public static final String MESSAGE_VIEW_STUDENT_SUCCESS = "Showing trend for student: %1$s";

    private final Index index;

    /**
     * Creates a ViewCommand to show the trend of the student at the specified {@code index}.
     * @param index of the student in the filtered student list to show the trend of
     */
    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX));
        }

        Student studentToShow = lastShownList.get(index.getZeroBased());
        return new CommandResult(generateSuccessMessage(studentToShow), true, studentToShow);
    }

    private String generateSuccessMessage(Student studentToShow) {
        return String.format(MESSAGE_VIEW_STUDENT_SUCCESS, studentToShow.getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ViewCommand)) {
            return false;
        }
        ViewCommand otherCommand = (ViewCommand) other;
        return index.equals(otherCommand.index);
    }
}
