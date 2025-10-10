package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Shows the trend of a student's performance over weeks.
 */
public class TrendCommand extends Command {
    public static final String COMMAND_WORD = "trend";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from trend");
    }
}
