package seedu.address.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.record.Record;
import seedu.address.model.record.ScoreType;
import seedu.address.model.record.WeekNumber;
import seedu.address.model.recordlist.RecordList;


/**
 * A UI component that displays information of a {@code Record}.
 */
public class ScoreCard extends UiPart<Region> {

    private static final String FXML = "ScoreCard.fxml";
    private static final String ATTENDANCE_LABEL = "Attendance Scores";
    private static final String PARTICIPATION_LABEL = "Participation Scores";
    private static final String SUBMISSION_LABEL = "Submission Scores";

    private RecordList recordList;
    private ScoreType scoreType;
    private final PropertyChangeListener recordListListener;

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

        // allow the injected RecordDisplay to grow vertically and horizontally
        recordDisplay.setMaxWidth(Double.MAX_VALUE);
        recordDisplay.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(recordDisplay, Priority.ALWAYS);
        VBox.setVgrow(recordDisplay, Priority.ALWAYS);

        // allow the card root to expand if placed in resizable containers
        cardPane.setMaxWidth(Double.MAX_VALUE);
        cardPane.setMaxHeight(Double.MAX_VALUE);

        // listener that updates UI on the JavaFX thread when RecordList changes
        this.recordListListener = (PropertyChangeEvent event) -> {
            Platform.runLater(() -> setRecordDisplay(this.scoreType));
        };
        this.recordList.addChangeListener(this.recordListListener);
    }

    public void setRecordDisplay(ScoreType scoreType) {
        Integer[] scores;
        switch (scoreType) {
        case ATTENDANCE:
            scores = recordList.getScores(Record::getAttendanceScore);
            scoreTypeLabel.setText(ATTENDANCE_LABEL);
            break;
        case PARTICIPATION:
            scores = recordList.getScores(Record::getParticipationScore);
            scoreTypeLabel.setText(PARTICIPATION_LABEL);
            break;
        case SUBMISSION:
            scores = recordList.getScores(Record::getSubmissionScore);
            scoreTypeLabel.setText(SUBMISSION_LABEL);
            break;
        default:
            scores = new Integer[WeekNumber.MAX_WEEK_NUMBER];
        }
        recordDisplay.displayScores(scores, scoreType);
    }
}
