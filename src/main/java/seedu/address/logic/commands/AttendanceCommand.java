package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ABSENCE_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK_NUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.record.AttendanceRecord;
import seedu.address.model.record.WeekNumber;

/**
 * Records the attendance of an existing student in TAHub.
 */
public class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "attend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Records the attendance of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_WEEK_NUMBER + "WEEK NUMBER] "
            + "[" + PREFIX_SCORE + "ATTENDANCE] "
            + "[" + PREFIX_ABSENCE_REASON + "REASON FOR ABSENCE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_WEEK_NUMBER + "3 "
            + PREFIX_SCORE + "0 "
            + PREFIX_ABSENCE_REASON + "MC";

    public static final String MESSAGE_STUDENT_ATTENDANCE_RECORDED_SUCCESS = "Attendance recorded for student: %1$s";
    public static final String MESSAGE_INVALID_INDEX = "Invalid index. Only positive integers are allowed.";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "The student at %d does not exist.";
    public static final String MESSAGE_INVALID_WEEK_NUMBER = "Invalid week number."
        + "Only positive integers in the range [1, 13] are allowed.";
    public static final String MESSAGE_INVALID_REASON_TEXT =
            "Reason for absence must contain alphanumeric characters and spaces only.";

    private final Index targetIndex;
    private final WeekNumber weekNumber;
    private final AttendanceRecord attendanceRecord;

    /**
     * @param targetIndex of the student in the filtered student list to record attendance for
     * @param weekNumber the week number of the attendance to be recorded
     * @param attendanceRecord the attendance record to be recorded
     */
    public AttendanceCommand(Index targetIndex, WeekNumber weekNumber, AttendanceRecord attendanceRecord) {
        requireNonNull(targetIndex);
        requireNonNull(weekNumber);
        requireNonNull(attendanceRecord);

        this.targetIndex = targetIndex;
        this.weekNumber = weekNumber;
        this.attendanceRecord = attendanceRecord;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO: Implement attendance logic
        return new CommandResult("Hello from attendance");
    }
}
