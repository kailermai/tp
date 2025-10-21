package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Shows the trend of the entire class across weeks.
 */
public class TrendCommand extends Command {

    public static final String COMMAND_WORD = "trend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows trend of all students.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_HELP_TITLE = "Shows the trend of the entire class:";
    public static final String MESSAGE_HELP_DESCRIPTION = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Opened trend window";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS, true);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof TrendCommand;
    }

}
