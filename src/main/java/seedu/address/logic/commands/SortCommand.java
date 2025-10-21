package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Sorts all students in TAHub base on their attendance or participation scores
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort all students base on attendance or participation.\n"
            + "Example: " + COMMAND_WORD + " /a , " +  COMMAND_WORD + " /p";

    public static final String MESSAGE_HELP = "Sort all students base on attendance or participation:\n"
            + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Sorted all students base on ";

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

    @Override
    public CommandResult execute(Model model) {
        if (byAttendance) {
            model.sortStudentByAttendance();
            return new CommandResult(SHOWING_HELP_MESSAGE + "attendance");
        } else {
            model.sortStudentByParticipation();
            return new CommandResult(SHOWING_HELP_MESSAGE + "participation");
        }
    }
}
