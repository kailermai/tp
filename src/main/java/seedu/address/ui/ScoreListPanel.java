package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import seedu.address.commons.core.LogsCenter;

import javafx.scene.layout.Region;
import javafx.scene.control.ListView;
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.SubmissionScore;
import seedu.address.model.recordlist.RecordList;
import seedu.address.model.record.Record;
import seedu.address.model.student.Student;

import java.util.List;
import java.util.logging.Logger;

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

    public ScoreListPanel(RecordList recordList) {
        super(FXML);
        setRecordList(recordList);
    }

    public ScoreListPanel(Student student) {
        super(FXML);
        setRecordList(student.getRecordList());
    }
    private void setRecordList(RecordList recordList) {
        ObservableList<Record> items = FXCollections.observableArrayList();
        List<Record> records = recordList.records;
        for (int i = 0; i < records.size(); i++) {
            // Add a default record if the record is null TODO: to be improved on
            if (records.get(i) == null) {
                items.add(new Record(new AttendanceScore(0), new SubmissionScore(0), new ParticipationScore(0)));
            } else {
                items.add(records.get(i));
            }
        }
        recordListView.setItems(items);
        recordListView.setCellFactory(listView -> new ScoreListPanel.ScoreListViewCell());
    }

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
