package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.Record;
import seedu.address.model.record.SubmissionScore;
import seedu.address.model.recordlist.RecordList;
import seedu.address.model.student.Student;

/**
 * Panel containing the list of scores.
 */
public class ScoreListPanel extends UiPart<Region> {
    private static final String FXML = "ScoreListPanel.fxml";
    private static final Logger logger = LogsCenter.getLogger(ScoreListPanel.class);

    @FXML
    private ListView<Record> recordListView;
    /**
     * Creates a {@code ScoreListPanel} with the given {@code ObservableList}.
     */
    public ScoreListPanel() {
        super(FXML);
    }
    /**
     * Creates a {@code ScoreListPanel} with the given {@code RecordList}.
     */
    public ScoreListPanel(RecordList recordList) {
        super(FXML);
        setRecordList(recordList);
    }
    /**
     * Creates a {@code ScoreListPanel} with the given {@code Student}.
     */
    public ScoreListPanel(Student student) {
        super(FXML);
        setRecordList(student.getRecordList());
    }
    /**
     * Sets the record list to be displayed.
     */
    private void setRecordList(RecordList recordList) {
        ObservableList<Record> items = FXCollections.observableArrayList();
        List<Record> records = recordList.records;
        for (int i = 0; i < records.size(); i++) {
            // Add a default record if the record is null
            if (records.get(i) == null) {
                items.add(new Record(new AttendanceScore(AttendanceScore.MIN_SCORE),
                        new SubmissionScore(SubmissionScore.MIN_SCORE),
                        new ParticipationScore(ParticipationScore.MIN_SCORE)));
            } else {
                items.add(records.get(i));
            }
        }
        recordListView.setItems(items);
        recordListView.setCellFactory(listView -> new ScoreListPanel.ScoreListViewCell());
    }
    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Record} using a {@code ScoreCard}.
     */
    class ScoreListViewCell extends ListCell<Record> {
        @Override
        protected void updateItem(Record record, boolean empty) {
            super.updateItem(record, empty);

            if (empty || record == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScoreCard(record, getIndex() + 1).getRoot());
            }
        }
    }
}
