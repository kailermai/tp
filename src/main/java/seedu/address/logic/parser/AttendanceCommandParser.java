package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ABSENCE_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK_NUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code AttendanceCommand} object
 */
public class AttendanceCommandParser implements Parser<AttendanceCommand> {

    /**
     * Parse the given {@code String} of arguments in the context of the {@code AttendanceCommand}
     * and returns an {@code AttendanceCommand} object for execution.
     * @return {@code AttendanceCommand}
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_WEEK_NUMBER, PREFIX_SCORE, PREFIX_ABSENCE_REASON);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE), ive);
        }

        String weekNumber = argMultimap.getValue(PREFIX_WEEK_NUMBER).orElse("");
        String score = argMultimap.getValue(PREFIX_SCORE).orElse("");
        String absenceReason = argMultimap.getValue(PREFIX_ABSENCE_REASON).orElse("");

        return new AttendanceCommand(index, weekNumber, score, absenceReason);
    }
}
