package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.Record;
import seedu.address.model.record.SubmissionScore;


/**
 * Jackson-friendly version of {@link Record}.
 */
public class JsonAdaptedRecord {

    private final Integer attendanceScore;
    private final Integer submissionScore;
    private final Integer participationScore;

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given {@code attendanceScore}, {@code submissionScore}
     * and {@code participationScore}.
     */
    @JsonCreator
    public JsonAdaptedRecord(@JsonProperty("attendanceScore") Integer attendanceScore,
            @JsonProperty("submissionScore") Integer submissionScore,
            @JsonProperty("participationScore") Integer participationScore) {
        this.attendanceScore = attendanceScore;
        this.submissionScore = submissionScore;
        this.participationScore = participationScore;
    }

    /**
     * Converts a given {@code Record} into this class for Jackson use.
     */
    public JsonAdaptedRecord(Record source) {

        if (source == null) {
            this.attendanceScore = null;
            this.submissionScore = null;
            this.participationScore = null;
            return;
        }

        this.attendanceScore = source.getAttendanceScore();
        this.submissionScore = source.getSubmissionScore();
        this.participationScore = source.getParticipationScore();
    }

    /**
     * Converts this Jackson-friendly adapted record object into the model's {@code Record} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted record.
     */
    public Record toModelType() throws IllegalValueException {

        boolean isNullRecord = attendanceScore == null
                || submissionScore == null
                || participationScore == null;

        if (isNullRecord) {
            return null;
        }

        return new Record(new AttendanceScore(attendanceScore), new SubmissionScore(submissionScore),
                new ParticipationScore(participationScore));
    }
}
