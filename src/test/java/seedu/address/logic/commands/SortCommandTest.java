package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

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
        CommandResult result = cmd.execute(model);

        // Verify user feedback
        String feedback = result.getFeedbackToUser();
        assertEquals(true, feedback != null && feedback.endsWith("attendance"),
                "Expected feedback to end with 'attendance', but was: " + feedback);
    }

    @Test
    public void execute_sortByParticipation_returnsParticipationMessage() {

        SortCommand cmd = SortCommand.sortCommandParticipation();
        CommandResult result = cmd.execute(model);

        // Verify user feedback
        String feedback = result.getFeedbackToUser();
        assertEquals(true, feedback != null && feedback.endsWith("participation"),
                "Expected feedback to end with 'participation', but was: " + feedback);
    }


}
