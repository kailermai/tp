package seedu.address.ui.panel;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.ui.StudentCard;
import seedu.address.ui.UiPart;

/**
 * Controller for a view page
 */
public class ViewPanel extends UiPart<Region> {
    private static final String FXML = "ViewPanel.fxml";

    private static final Logger logger = LogsCenter.getLogger(ViewPanel.class);

    private ScoreListPanel scoreListPanel;
    private StudentCard studentCard;
    private Student student;


    @FXML
    private StackPane scoreListPanelPlaceholder;

    @FXML
    private StackPane studentCardPlaceholder;

    public ViewPanel() {
        super(FXML);
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
        // Clear previous content if any so no overlapping occurs
        studentCardPlaceholder.getChildren().clear();
        scoreListPanelPlaceholder.getChildren().clear();

        scoreListPanel = new ScoreListPanel(student);
        scoreListPanelPlaceholder.getChildren().add(scoreListPanel.getRoot());

        studentCard = new StudentCard(student);
        studentCardPlaceholder.getChildren().add(studentCard.getRoot());
    }
}
