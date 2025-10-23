package seedu.address.ui.panel;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.student.Student;
import seedu.address.ui.TrendCard;
import seedu.address.ui.UiPart;

/**
 * Panel containing the trend of students.
 */
public class TrendListPanel extends UiPart<Region> {
    private static final String FXML = "TrendListPanel.fxml";

    @FXML
    private ListView<Student> recordsListView;

    /**
     * Creates a {@code TrendListPanel} with the given {@code ObservableList}.
     */
    public TrendListPanel(ObservableList<Student> studentList) {
        super(FXML);
        recordsListView.setItems(studentList);
        recordsListView.setCellFactory(listView -> new TrendListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code TrendCard}.
     */
    class TrendListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                Region trendCardRoot = new TrendCard(student).getRoot();
                setGraphic(trendCardRoot);
            }
        }
    }

}
