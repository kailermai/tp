package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.record.Record;
import seedu.address.model.record.WeekNumber;
import seedu.address.model.recordlist.RecordList;
import seedu.address.ui.RecordDisplay.ScoreType;

/**
 * A UI component that displays information of a {@code Record}.
 */
public class ScoreCard extends UiPart<Region> {

    private static final String FXML = "ScoreCard.fxml";

    private RecordList recordList;
    private ScoreType scoreType;

    @FXML
    private HBox cardPane;
    @FXML
    private RecordDisplay recordDisplay;
    @FXML
    private Label scoreTypeLabel;

    /**
     * Creates a {@code ScoreCard} with the given {@code Record} to display.
     */
    public ScoreCard(RecordList recordList, ScoreType scoreType) {
        super(FXML);
        this.recordList = recordList;
        this.scoreType = scoreType;
        setRecordDisplay(scoreType);
    }

    public void setRecordDisplay(ScoreType scoreType) {
        Integer[] scores;
        switch (scoreType) {
        case ATTENDANCE:
            scores = recordList.getScores(Record::getAttendanceScore);
            scoreTypeLabel.setText("Attendance Scores");
            break;
        case PARTICIPATION:
            scores = recordList.getScores(Record::getParticipationScore);
            scoreTypeLabel.setText("Participation Scores");
            break;
        case SUBMISSION:
            scores = recordList.getScores(Record::getSubmissionScore);
            scoreTypeLabel.setText("Submission Scores");
            break;
        default:
            scores = new Integer[WeekNumber.MAX_WEEK_NUMBER];
        }
        recordDisplay.displayScores(scores, scoreType);
    }
}
