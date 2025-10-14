package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.index.Index;
import seedu.address.model.record.Record;
import seedu.address.model.record.WeekNumber;
import seedu.address.model.recordlist.RecordList;
import seedu.address.model.student.Student;

public class TrendCard extends UiPart<Region> {
    private static final String FXML = "TrendCard.fxml";
    private final RecordList recordList;
    @FXML
    private HBox cardPane;
    @FXML
    private Label studentName;
    @FXML
    private Label trend;

    /**
     * Creates a {@code ScoreCard} with the given {@code Record} to display.
     */
    public TrendCard(Student student) {
        super(FXML);
        recordList = student.getRecordList();
        studentName.setText(student.getName().toString());
        trend.setText(getOverallRecord(student));
    }

    public String getOverallRecord(Student student) {
        RecordList recordList = student.getRecordList();
        int attendanceTotal = 0;
        int participationTotal = 0;
        int submissionTotal = 0;

        for (int i = 0; i < WeekNumber.MAX_WEEK_NUMBER; i++) {
            Index currentIndex = Index.fromZeroBased(i);
            Record record = this.recordList.getRecord(currentIndex);
            if (record != null) {
                attendanceTotal += record.getAttendanceScore().getScore();
                participationTotal += record.getParticipationScore().getScore();
                submissionTotal += record.getSubmissionScore().getScore();
            }
        }
        return "Attendance: " + attendanceTotal + "/13 | Participation: " + participationTotal + "/65 | Submission: "
                + submissionTotal + "/13";
    }
}
