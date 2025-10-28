package seedu.address.ui.panel;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.record.ScoreType;
import seedu.address.model.recordlist.RecordList;
import seedu.address.model.student.Student;
import seedu.address.ui.ScoreCard;
import seedu.address.ui.UiPart;


/**
 * Panel containing the list of scores.
 */
public class ScoreListPanel extends UiPart<Region> {
    private static final String FXML = "ScoreListPanel.fxml";
    private static final Logger logger = LogsCenter.getLogger(ScoreListPanel.class);

    private final RecordList recordList;

    @FXML
    private ListView<ScoreType> recordListView;

    /**
     * Creates a {@code ScoreListPanel} with the given {@code RecordList}.
     */
    public ScoreListPanel(RecordList recordList) {
        super(FXML);
        this.recordList = recordList;
        setRecordList(recordList);
    }
    /**
     * Creates a {@code ScoreListPanel} with the given {@code Student}.
     */
    public ScoreListPanel(Student student) {
        super(FXML);
        this.recordList = student.getRecordList();
        setRecordList(recordList);
    }
    /**
     * Sets the record list to be displayed.
     */
    private void setRecordList(RecordList recordList) {
        ObservableList<ScoreType> items = FXCollections.observableArrayList();
        items.addAll(
                ScoreType.ATTENDANCE,
                ScoreType.PARTICIPATION,
                ScoreType.SUBMISSION
        );
        recordListView.setItems(items);
        recordListView.setCellFactory(listView -> new ScoreListPanel.ScoreListViewCell());
    }
    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Record} using a {@code ScoreCard}.
     */
    class ScoreListViewCell extends ListCell<ScoreType> {
        @Override
        protected void updateItem(ScoreType scoreType, boolean empty) {
            super.updateItem(scoreType, empty);

            if (empty || scoreType == null) {
                setGraphic(null);
                setText(null);
            } else {
                Region scoreCardRoot = new ScoreCard(recordList, scoreType).getRoot();
                scoreCardRoot.getStyleClass().add("scorecard");
                setGraphic(scoreCardRoot);
            }
        }
    }
}
