package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals_contract() {
        SortCommand attendance1 = SortCommand.sortCommandAttendance();
        SortCommand attendance2 = SortCommand.sortCommandAttendance();
        SortCommand participation1 = SortCommand.sortCommandParticipation();
        SortCommand participation2 = SortCommand.sortCommandParticipation();

        // same values -> equal
        assertEquals(attendance1, attendance2);
        assertEquals(participation1, participation2);

        // different values -> not equal
        assertNotEquals(attendance1, participation1);

        // null -> not equal
        assertNotEquals(attendance1, null);

        // different type -> not equal
        assertNotEquals(attendance1, 5);
    }

    @Test
    public void execute_sortByAttendance_returnsAttendanceMessage() {

        SortCommand cmd = SortCommand.sortCommandAttendance();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(cmd, model, SortCommand.MESSAGE_SUCCESS_ATTENDANCE, expectedModel);
    }

    @Test
    public void execute_sortByParticipation_returnsParticipationMessage() {

        SortCommand cmd = SortCommand.sortCommandParticipation();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(cmd, model, SortCommand.MESSAGE_SUCCESS_PARTICIPATION, expectedModel);
    }


}
