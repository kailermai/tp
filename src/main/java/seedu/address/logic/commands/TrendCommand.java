package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Shows the trend of the entire class across weeks.
 */
public class TrendCommand extends Command {

    public static final String COMMAND_WORD = "trend";

    public static final String MESSAGE_HELP = "Shows the trend of all students:\n"
            + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Trend window opened";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof TrendCommand;
    }

}
