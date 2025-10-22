package seedu.address.ui.panel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.ui.UiPart;

/**
 * Controller for a trend page
 */
public class TrendPanel extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(TrendPanel.class);
    private static final String FXML = "TrendPanel.fxml";

    @FXML
    private StackPane trendListPanelPlaceholder;

    public TrendPanel() {
        super(FXML);
    }

    /**
     * Fills the inner parts of the trend window.
     */
    public void fillInnerPart(ObservableList<Student> studentList) {
        TrendListPanel trendListPanel = new TrendListPanel(studentList);
        trendListPanelPlaceholder.getChildren().add(trendListPanel.getRoot());
    }
}

