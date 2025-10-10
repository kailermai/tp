package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Shows the trend of a student's performance over weeks.
 */
public class TrendCommand extends Command {
    public static final String COMMAND_WORD = "trend";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the trend of a student's performance over weeks.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Trend feature is not implemented yet.";

    public static final String MESSAGE_ARGUMENTS = "INDEX: %1$d";

    private final Index index;

    /**
     * Creates a TrendCommand to show the trend of the student at the specified {@code index}.
     * @param index of the student in the filtered student list to show the trend of
     */
    public TrendCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from trend");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof TrendCommand)) {
            return false;
        }
        TrendCommand otherCommand = (TrendCommand) other;
        return index.equals(otherCommand.index);
    }
}
