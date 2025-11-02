package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_PARTICIPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_SUBMISSION;

import seedu.address.model.Model;


/**
 * Sorts all students in TAHub based on their attendance or participation or submission scores percentage
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort all students based on attendance or participation or submission percentages.\n"
            + "Parameters: " + PREFIX_SORT_BY + PREFIX_SORT_ATTENDANCE
            + " for attendance or " + PREFIX_SORT_BY + PREFIX_SORT_PARTICIPATION
            + " for participation or " + PREFIX_SORT_BY + PREFIX_SORT_SUBMISSION
            + " for submission\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SORT_BY + PREFIX_SORT_ATTENDANCE
            + ", " + COMMAND_WORD + " " + PREFIX_SORT_BY + PREFIX_SORT_PARTICIPATION
            + ", " + COMMAND_WORD + " " + PREFIX_SORT_BY + PREFIX_SORT_SUBMISSION;

    public static final String MESSAGE_HELP_TITLE =
            "Sort all students based on attendance or participation or submission percentages:\n";

    public static final String MESSAGE_HELP_DESCRIPTION =
            COMMAND_WORD + " {" + PREFIX_SORT_BY + PREFIX_SORT_ATTENDANCE + "|"
            + PREFIX_SORT_BY + PREFIX_SORT_PARTICIPATION + "|"
            + PREFIX_SORT_BY + PREFIX_SORT_SUBMISSION + "}";

    public static final String MESSAGE_SUCCESS_ATTENDANCE = "Sorted all students based on attendance.";

    public static final String MESSAGE_SUCCESS_PARTICIPATION = "Sorted all students based on participation.";

    public static final String MESSAGE_SUCCESS_SUBMISSION = "Sorted all students based on submission.";

    private boolean byAttendance;

    private boolean byParticipation;

    private boolean bySubmission;

    /**
     * Constructs SortCommand
     * @param byAttendance whether it is sort by attendance scores
     * @param byParticipation whether it is sort by participation scores
     */
    public SortCommand(boolean byAttendance, boolean byParticipation, boolean bySubmission) {
        this.byAttendance = byAttendance;
        this.byParticipation = byParticipation;
        this.bySubmission = bySubmission;
    }

    public static SortCommand sortCommandAttendance() {
        return new SortCommand(true, false, false);
    }

    public static SortCommand sortCommandParticipation() {
        return new SortCommand(false, true, false);
    }

    public static SortCommand sortCommandSubmission() {
        return new SortCommand(false, false, true);
    }

    @Override
    public CommandResult execute(Model model) {
        if (byAttendance) {
            model.sortStudentByAttendance();
            return new CommandResult(MESSAGE_SUCCESS_ATTENDANCE);
        } else if (byParticipation) {
            model.sortStudentByParticipation();
            return new CommandResult(MESSAGE_SUCCESS_PARTICIPATION);
        } else {
            model.sortStudentBySubmission();
            return new CommandResult(MESSAGE_SUCCESS_SUBMISSION);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SortCommand)) {
            return false;
        }
        SortCommand otherCommand = (SortCommand) other;
        return this.byAttendance == otherCommand.byAttendance
                && this.byParticipation == otherCommand.byParticipation
                && this.bySubmission == otherCommand.bySubmission;
    }
}
