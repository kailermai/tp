package seedu.address.model.recordlist;

import seedu.address.model.record.AttendanceRecord;

/**
 * Represents the list of all types of records, namely Attendance, Submission and Participation records.
 */
public class RecordList {
    private final static int WEEKS = 13;
    private AttendanceRecord[] attendanceRecords;
    // private SubmissionRecord[] submissionRecords;
    // private ParticipationRecord[] participationRecords;

    /**
     * Constructs a {@code RecordList} object with specified maximum size.
     */
    public RecordList() {
        attendanceRecords = new AttendanceRecord[WEEKS];
        // instantiate submissionRecords
        // instantiate participationRecords
    }


}
