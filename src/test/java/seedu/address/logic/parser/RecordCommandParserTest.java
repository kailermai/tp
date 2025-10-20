package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_SCORE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ATTENDANCE_SCORE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PARTICIPATION_SCORE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SUBMISSION_SCORE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WEEK_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PARTICIPATION_SCORE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.RECORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SUBMISSION_SCORE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.WEEK_NUMBER_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBMISSION_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK_NUMBER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.RecordCommand;
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.Record;
import seedu.address.model.record.SubmissionScore;
import seedu.address.model.record.WeekNumber;

public class RecordCommandParserTest {
    private RecordCommandParser parser = new RecordCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + WEEK_NUMBER_DESC + RECORD_DESC;
        Record expectedRecord = new Record(new AttendanceScore(AttendanceScore.MAX_SCORE),
                new SubmissionScore(SubmissionScore.MAX_SCORE), new ParticipationScore(ParticipationScore.MAX_SCORE));
        RecordCommand expectedCommand = new RecordCommand(INDEX_FIRST_STUDENT, new WeekNumber(1), expectedRecord);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_duplicateFields_failure() {

        String expectedMessageDuplicateWeekNumber = Messages.getErrorMessageForDuplicatePrefixes(PREFIX_WEEK_NUMBER);
        String inputWithDuplicateWeekNumber = INDEX_FIRST_STUDENT.getOneBased() + WEEK_NUMBER_DESC + WEEK_NUMBER_DESC
                + RECORD_DESC;

        assertParseFailure(parser, inputWithDuplicateWeekNumber, expectedMessageDuplicateWeekNumber);

        String expectedMessageDuplicateRecord = Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ATTENDANCE_SCORE,
                PREFIX_SUBMISSION_SCORE, PREFIX_PARTICIPATION_SCORE);
        String inputWithDuplicateRecord = INDEX_FIRST_STUDENT.getOneBased() + WEEK_NUMBER_DESC
                + RECORD_DESC + RECORD_DESC;

        assertParseFailure(parser, inputWithDuplicateRecord, expectedMessageDuplicateRecord);
    }

    @Test
    public void parse_noScoresProvided_success() {
        RecordCommand expectedCommand = new RecordCommand(
                INDEX_FIRST_STUDENT, new WeekNumber(WeekNumber.MIN_WEEK_NUMBER), null);
        String inputWithoutRecord = INDEX_FIRST_STUDENT.getOneBased() + WEEK_NUMBER_DESC;

        assertParseSuccess(parser, inputWithoutRecord, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecordCommand.MESSAGE_USAGE);
        String inputWithoutIndex = WEEK_NUMBER_DESC + RECORD_DESC;
        String inputWithoutWeekNumber = INDEX_FIRST_STUDENT.getOneBased() + RECORD_DESC;
        String inputOneAttendanceScore = INDEX_FIRST_STUDENT.getOneBased() + WEEK_NUMBER_DESC + ATTENDANCE_SCORE_DESC;
        String inputOneSubmissionScore = INDEX_FIRST_STUDENT.getOneBased() + WEEK_NUMBER_DESC + SUBMISSION_SCORE_DESC;
        String inputOneParticipationScore = INDEX_FIRST_STUDENT.getOneBased() + WEEK_NUMBER_DESC
                + PARTICIPATION_SCORE_DESC;
        String inputWithTwoScores = INDEX_FIRST_STUDENT.getOneBased() + WEEK_NUMBER_DESC + ATTENDANCE_SCORE_DESC
                + SUBMISSION_SCORE_DESC;

        // missing index
        assertParseFailure(parser, inputWithoutIndex, expectedMessage);

        // missing week number
        assertParseFailure(parser, inputWithoutWeekNumber, expectedMessage);

        // some records provided
        assertParseFailure(parser, inputOneSubmissionScore, expectedMessage);
        assertParseFailure(parser, inputOneAttendanceScore, expectedMessage);
        assertParseFailure(parser, inputOneParticipationScore, expectedMessage);
        assertParseFailure(parser, inputWithTwoScores, expectedMessage);
    }

    @Test
    public void parse_invalidParameters_failure() {

        // invalid week number
        String inputWithInvalidWeekNumber = INDEX_FIRST_STUDENT.getOneBased() + INVALID_WEEK_NUMBER_DESC + RECORD_DESC;
        assertParseFailure(parser, inputWithInvalidWeekNumber, WeekNumber.MESSAGE_CONSTRAINTS);

        // invalid attendance score
        String inputWithInvalidAttendanceScore = INDEX_FIRST_STUDENT.getOneBased() + WEEK_NUMBER_DESC
                + INVALID_ATTENDANCE_SCORE_DESC + SUBMISSION_SCORE_DESC + PARTICIPATION_SCORE_DESC;
        assertParseFailure(parser, inputWithInvalidAttendanceScore, AttendanceScore.MESSAGE_CONSTRAINTS);

        //invalid submission score
        String inputWithInvalidSubmissionScore = INDEX_FIRST_STUDENT.getOneBased() + WEEK_NUMBER_DESC
                + ATTENDANCE_SCORE_DESC + INVALID_SUBMISSION_SCORE_DESC + PARTICIPATION_SCORE_DESC;
        assertParseFailure(parser, inputWithInvalidSubmissionScore, SubmissionScore.MESSAGE_CONSTRAINTS);

        //invalid participation score
        String inputWithInvalidParticipationScore = INDEX_FIRST_STUDENT.getOneBased() + WEEK_NUMBER_DESC
                + ATTENDANCE_SCORE_DESC + SUBMISSION_SCORE_DESC + INVALID_PARTICIPATION_SCORE_DESC;
        assertParseFailure(parser, inputWithInvalidParticipationScore, ParticipationScore.MESSAGE_CONSTRAINTS);
    }
}
