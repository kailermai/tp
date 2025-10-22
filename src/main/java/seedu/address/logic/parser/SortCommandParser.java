package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_PARTICIPATION;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parse the given {@code String} of arguments in the context of the {@code SortCommand}
     *       and returns an {@code SortCommand} object for execution.
     * @return {@code SortCommand}
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_SORT_BY);
        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SORT_BY);
        if (!argumentMultimap.getValue(PREFIX_SORT_BY).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        String sortType;
        try {
            sortType = ParserUtil.parseSortType(argumentMultimap.getValue(PREFIX_SORT_BY).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), pe);
        }

        if (sortType.equals(PREFIX_SORT_ATTENDANCE.toString())) {
            return SortCommand.sortCommandAttendance();
        } else if (sortType.equals(PREFIX_SORT_PARTICIPATION.toString())) {
            return SortCommand.sortCommandParticipation();
        } else {
            return SortCommand.sortCommandSubmission();
        }
    }
}
