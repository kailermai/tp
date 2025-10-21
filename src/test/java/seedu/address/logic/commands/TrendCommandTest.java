package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class TrendCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_trend_success() {
        CommandResult expectedCommandResult = new CommandResult(TrendCommand.MESSAGE_SUCCESS, true);
        assertCommandSuccess(new TrendCommand(), model, expectedCommandResult, expectedModel);
    }
}
