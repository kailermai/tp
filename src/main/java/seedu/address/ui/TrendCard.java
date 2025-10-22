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
    private static final double SCORE_THRESHOLD_LOW = 0.5;
    private static final double SCORE_THRESHOLD_MEDIUM = 0.8;
    private static final String COLOR_SCORE_HIGH = "#00e676";
    private static final String COLOR_SCORE_MEDIUM = "#fbc531";
    private static final String COLOR_SCORE_LOW = "#ff6b6b";
    private final RecordList recordList;

    @FXML
    private HBox cardPane;
    @FXML
    private Label studentName;
    @FXML
    private Label attendanceLabel;
    @FXML
    private Label participationLabel;
    @FXML
    private Label submissionLabel;


    /**
     * Creates a {@code TrendCard} with the given {@code student} to display.
     */
    public TrendCard(Student student) {
        super(FXML);
        recordList = student.getRecordList();
        studentName.setText(student.getName().toString());
        setOverallRecord(student);
    }

    private void setOverallRecord(Student student) {
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

        // Set text
        attendanceLabel.setText(attendanceTotal + "/" + totalNumberOfScores);
        participationLabel.setText(String.format("%.2f/5", participationAverage));
        submissionLabel.setText(submissionTotal + "/" + totalNumberOfScores);

        // Apply color based on percentage
        setColorByPercentage(attendanceLabel, attendanceTotal, totalNumberOfScores);
        setColorByPercentage(participationLabel, participationAverage, 5.0);
        setColorByPercentage(submissionLabel, submissionTotal, totalNumberOfScores);
    }

    private void setColorByPercentage(Label label, double score, double total) {
        double percent = (total == 0) ? 0 : score / total;

        String color;
        if (percent < SCORE_THRESHOLD_LOW) {
            color = COLOR_SCORE_LOW;
        } else if (percent < SCORE_THRESHOLD_MEDIUM) {
            color = COLOR_SCORE_MEDIUM;
        } else {
            color = COLOR_SCORE_HIGH;
        }

        label.setStyle("-fx-text-fill: " + color + ";");
    }
}
