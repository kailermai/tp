package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * Controller for a help page
 */
public class TrendWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "TrendWindow.fxml";

    @FXML
    private StackPane trendListPanelPlaceholder;

    /**
     * Creates a new TrendWindow.
     * @param root Stage to use as the root of the TrendWindow
     */
    public TrendWindow(Stage root) {
        super(FXML, root);
        root.setWidth(500);
        root.setHeight(500);
        root.setResizable(true);
    }

    /**
     * Creates a new TrendWindow.
     */
    public TrendWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     */
    public void show() {
        logger.fine("Showing trend page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the trend window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the trend window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the trend window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Fills the inner parts of the trend window.
     */
    public void fillInnerPart(ObservableList<Student> studentList) {
        TrendListPanel trendListPanel = new TrendListPanel(studentList);
        trendListPanelPlaceholder.getChildren().add(trendListPanel.getRoot());
    }
}
