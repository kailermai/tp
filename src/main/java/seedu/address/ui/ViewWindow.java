package seedu.address.ui;

import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

import java.util.logging.Logger;

public class ViewWindow extends UiPart<Stage> {
    private static final String FXML = "ViewWindow.fxml";

    private static final Logger logger = LogsCenter.getLogger(ViewWindow.class);
    private final Student student;

    /**
     * Creates a new ViewWindow.
     *
     * @param root Stage to use as the root of the ViewWindow.
     * @param student Student whose details are to be displayed.
     */
    public ViewWindow(Stage root, Student student) {
        super(FXML, root);
        this.student = student;
    }
    /**
     * Creates a new ViewWindow.
     *
     * @param root Stage to use as the root of the TrendWindow.
     */
    public ViewWindow(Stage root) {
        super(FXML, root);
        student = null;
    }
    /**
     * Creates a new TrendWindow.
     */
    public ViewWindow() {
        this(new Stage());
    }

    /**
     * Creates a new ViewWindow.
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }
    
    /**
     * Returns true if the view window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }
    /**
     * Hides the view window.
     */
    public void hide() {
        getRoot().hide();
    }
    /**
     * Focuses on the view window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
