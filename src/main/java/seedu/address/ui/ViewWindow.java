package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * Controller for a view page
 */
public class ViewWindow extends UiPart<Stage> {
    private static final String FXML = "ViewWindow.fxml";

    private static final Logger logger = LogsCenter.getLogger(ViewWindow.class);

    private ScoreListPanel scoreListPanel;
    private StudentCard studentCard;
    private Student student;

    @FXML
    private StackPane scoreListPanelPlaceholder;

    @FXML
    private StackPane studentCardPlaceholder;
    /**
     * Creates a new ViewWindow.
     *
     * @param root Stage to use as the root of the ViewWindow.
     */
    public ViewWindow(Stage root) {
        super(FXML, root);
        root.setWidth(500);
        root.setHeight(500);
        root.setResizable(true);
    }
    /**
     * Creates a new ViewWindow.
     */
    public ViewWindow() {
        this(new Stage());
    }

    /**
     * Creates a new ViewWindow.
     */
    public void show() {
        logger.fine("Viewing student.");
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
    /**
     * Sets the student to be displayed in the view window.
     */
    public void setStudent(Student student) {
        this.student = student;
    }
    /**
     * Fills the inner parts of the view window.
     */
    public void fillInnerPart() {
        scoreListPanel = new ScoreListPanel(student);
        scoreListPanelPlaceholder.getChildren().add(scoreListPanel.getRoot());

        studentCard = new StudentCard(student);
        studentCardPlaceholder.getChildren().add(studentCard.getRoot());
    }
}
