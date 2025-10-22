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

/**
 * A UI component that displays the overall record information of a {@code Student}.
 */
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
     * Creates a {@code TrendCard} with the given {@code student} to display.
     */
    public TrendCard(Student student) {
        super(FXML);
        recordList = student.getRecordList();
        studentName.setText(student.getName().toString());
        trend.setText(getOverallRecord(student));
    }

    private String getOverallRecord(Student student) {
        int totalNumberOfScores = 0;
        int attendanceTotal = 0;
        double participationTotal = 0;
        int submissionTotal = 0;

        for (int i = 0; i < WeekNumber.MAX_WEEK_NUMBER; i++) {
            Index currentIndex = Index.fromZeroBased(i);
            Record record = this.recordList.getRecord(currentIndex);
            if (record == null) {
                continue;
            }
            totalNumberOfScores += 1;
            attendanceTotal += record.getAttendanceScore();
            participationTotal += record.getParticipationScore();
            submissionTotal += record.getSubmissionScore();
        }

        double participationAverage = totalNumberOfScores == 0 ? 0 : participationTotal / totalNumberOfScores;

        return "Attendance: " + attendanceTotal + "/" + totalNumberOfScores + " | Average participation: "
                + String.format("%.2f", participationAverage) + "/5 | Submission: " + submissionTotal + "/"
                + totalNumberOfScores;
    }
}
