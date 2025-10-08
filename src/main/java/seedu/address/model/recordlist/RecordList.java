package seedu.address.model.recordlist;

import java.util.ArrayList;

import seedu.address.model.record.AttendanceRecord;

/**
 * Represents the list of all types of records, namely Attendance, Submission and Participation records.
 */
public class RecordList {
    private static final int WEEKS = 13;
    private ArrayList<AttendanceRecord> attendanceRecords;
    // private ArrayList<SubmissionRecord> submissionRecords;
    // private ParticipationList<ParticipationRecord> participationRecords;

    /**
     * Constructs a {@code RecordList} object with specified maximum size.
     */
    public RecordList() {
        attendanceRecords = new ArrayList<>();
        // instantiate submissionRecords
        // instantiate participationRecords
    }


}
