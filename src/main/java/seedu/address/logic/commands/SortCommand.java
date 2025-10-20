package seedu.address.logic.commands;

import seedu.address.model.Model;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort all students base on attendance and participation.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_HELP = "Sort all students base on attendance and participation:\n"
            + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened sort window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(true, SHOWING_HELP_MESSAGE);
    }
}
