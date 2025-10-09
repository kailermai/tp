package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBMISSION_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK_NUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.Record;
import seedu.address.model.record.SubmissionScore;
import seedu.address.model.record.WeekNumber;

/**
 * Parses input arguments and creates a new {@code RecordCommandParser} object
 */
public class RecordCommandParser implements Parser<RecordCommand> {
    /**
     * Parse the given {@code String} of arguments in the context of the {@code RecordCommand}
     * and returns an {@code RecordCommand} object for execution.
     * @return {@code RecordCommand}
     * @throws ParseException if the user input does not conform to the expected format
     */
    public RecordCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_WEEK_NUMBER, PREFIX_ATTENDANCE_SCORE, PREFIX_PARTICIPATION_SCORE, PREFIX_SUBMISSION_SCORE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecordCommand.MESSAGE_USAGE), ive);
        }

        WeekNumber weekNumber = ParserUtil.parseWeekNumber(argMultimap.getValue(PREFIX_WEEK_NUMBER).orElse(""));
        AttendanceScore attendanceScore = ParserUtil.parseAttendanceScore(
                argMultimap.getValue(PREFIX_ATTENDANCE_SCORE).orElse(""));
        SubmissionScore submissionScore = ParserUtil.parseSubmissionScore(
                argMultimap.getValue(PREFIX_SUBMISSION_SCORE).orElse(""));
        ParticipationScore participationScore = ParserUtil.parseParticipationScore(
                argMultimap.getValue(PREFIX_PARTICIPATION_SCORE).orElse(""));

        return new RecordCommand(index, weekNumber, new Record(attendanceScore, submissionScore, participationScore));
    }
}
