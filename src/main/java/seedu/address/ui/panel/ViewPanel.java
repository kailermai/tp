package seedu.address.ui.panel;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
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
    private ListChangeListener<Student> filteredListener;

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
    public void setStudent(Logic logic, Index studentIndex) {
        ObservableList<Student> filtered = logic.getFilteredStudentList();
        this.student = filtered.get(studentIndex.getZeroBased());
        if (filteredListener != null) {
            filtered.removeListener(filteredListener);
        }
        filteredListener = change -> Platform.runLater(() -> {
            while (change.next()) {
                if (change.wasReplaced()) {
                    checkReplacement(change);
                    return;
                }
            }
            // Handle deletion or disappearance
            if (!filtered.contains(student)) {
                showPlaceholders(false);
            }
        });

        filtered.addListener(filteredListener);
    }
    /**
     * Fills the inner parts of the view window.
     */
    public void fillInnerPart() {
        showPlaceholders(true);
        // Clear previous content if any so no overlapping occurs
        studentCardPlaceholder.getChildren().clear();
        scoreListPanelPlaceholder.getChildren().clear();

        scoreListPanel = new ScoreListPanel(student);
        scoreListPanelPlaceholder.getChildren().add(scoreListPanel.getRoot());

        studentCard = new StudentCard(student);
        studentCardPlaceholder.getChildren().add(studentCard.getRoot());
    }

    private void showPlaceholders(boolean show) {
        studentCardPlaceholder.setVisible(show);
        scoreListPanelPlaceholder.setVisible(show);
        studentCardPlaceholder.setManaged(show);
        scoreListPanelPlaceholder.setManaged(show);
    }

    private <T> void checkReplacement(ListChangeListener.Change<T> change) {
        for (T oldS : change.getRemoved()) {
            if (oldS.equals(student)) {
                student = (Student) change.getAddedSubList().get(change.getRemoved().indexOf(oldS));
                fillInnerPart();
                return;
            }
        }
    }
}
