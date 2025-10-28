package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBMISSION_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK_NUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.Record;
import seedu.address.model.record.ScoreType;
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

        if (!isValidPrefixCombination(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecordCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_WEEK_NUMBER, PREFIX_ATTENDANCE_SCORE, PREFIX_PARTICIPATION_SCORE, PREFIX_SUBMISSION_SCORE);

        WeekNumber weekNumber = ParserUtil.parseWeekNumber(argMultimap.getValue(PREFIX_WEEK_NUMBER).orElse(""));

        // isValidPrefixCombination ensures that if one score is not present, all other scores are not present as well
        boolean noScoresProvided = argMultimap.getValue(PREFIX_ATTENDANCE_SCORE).isEmpty();
        if (noScoresProvided) {
            return new RecordCommand(index, weekNumber, null);
        }

        AttendanceScore attendanceScore = ParserUtil.parseScore(
                argMultimap.getValue(PREFIX_ATTENDANCE_SCORE).orElse(""), ScoreType.ATTENDANCE);
        SubmissionScore submissionScore = ParserUtil.parseScore(
                argMultimap.getValue(PREFIX_SUBMISSION_SCORE).orElse(""), ScoreType.SUBMISSION);
        ParticipationScore participationScore = ParserUtil.parseScore(
                argMultimap.getValue(PREFIX_PARTICIPATION_SCORE).orElse(""), ScoreType.PARTICIPATION);

        return new RecordCommand(index, weekNumber, new Record(attendanceScore, submissionScore, participationScore));
    }

    /**
     * Returns true if the combination of prefixes in the given {@code ArgumentMultimap} is valid.
     * The combination is valid if it contains {@code PREFIX_WEEK_NUMBER} and at least one of
     * {@code PREFIX_ATTENDANCE_SCORE}, {@code PREFIX_PARTICIPATION_SCORE} or {@code PREFIX_SUBMISSION_SCORE}.
     * @param argumentMultimap The {@code ArgumentMultimap} to check.
     */
    private static boolean isValidPrefixCombination(ArgumentMultimap argumentMultimap) {
        boolean hasWeek = argumentMultimap.getValue(PREFIX_WEEK_NUMBER).isPresent();

        boolean hasAttendance = argumentMultimap.getValue(PREFIX_ATTENDANCE_SCORE).isPresent();
        boolean hasSubmission = argumentMultimap.getValue(PREFIX_SUBMISSION_SCORE).isPresent();
        boolean hasParticipation = argumentMultimap.getValue(PREFIX_PARTICIPATION_SCORE).isPresent();

        boolean hasAtLeastOneScore = hasAttendance || hasSubmission || hasParticipation;
        boolean hasAllScoresPresent = hasAttendance && hasSubmission && hasParticipation;

        if (!hasWeek) {
            return false;
        }

        if (hasAtLeastOneScore) {
            return hasAllScoresPresent;
        }

        return true;
    }

}
