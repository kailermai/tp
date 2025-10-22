package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_PARTICIPATION;

import seedu.address.model.Model;


/**
 * Sorts all students in TAHub based on their attendance or participation scores
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort all students based on attendance or participation.\n"
            + "Parameters: " + PREFIX_SORT_BY + PREFIX_SORT_ATTENDANCE
            + " for attendance or " + PREFIX_SORT_BY + PREFIX_SORT_PARTICIPATION
            + " for participation\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SORT_BY + PREFIX_SORT_ATTENDANCE
            + " " + COMMAND_WORD + " " + PREFIX_SORT_BY + PREFIX_SORT_PARTICIPATION;

    public static final String MESSAGE_HELP_TITLE = "Sort all students based on attendance or participation.\n";

    public static final String MESSAGE_HELP_DESCRIPTION = COMMAND_WORD + " /a for attendance or "
            + COMMAND_WORD + " /p for participation";

    public static final String MESSAGE_SUCCESS_ATTENDANCE = "Sorted all students based on attendance";

    public static final String MESSAGE_SUCCESS_PARTICIPATION = "Sorted all students based on participation";

    private boolean byAttendance;

    private boolean byParticipation;

    /**
     * Constructs SortCommand
     * @param byAttendance whether it is sort by attendance scores
     * @param byParticipation whether it is sort by participation scores
     */
    public SortCommand(boolean byAttendance, boolean byParticipation) {
        this.byAttendance = byAttendance;
        this.byParticipation = byParticipation;
    }

    public static SortCommand sortCommandAttendance() {
        return new SortCommand(true, false);
    }

    public static SortCommand sortCommandParticipation() {
        return new SortCommand(false, true);
    }

    @Override
    public CommandResult execute(Model model) {
        if (byAttendance) {
            model.sortStudentByAttendance();
            return new CommandResult(MESSAGE_SUCCESS_ATTENDANCE);
        } else {
            model.sortStudentByParticipation();
            return new CommandResult(MESSAGE_SUCCESS_PARTICIPATION);
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
                && this.byParticipation == otherCommand.byParticipation;
    }
}
