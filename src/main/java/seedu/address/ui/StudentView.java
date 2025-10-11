package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * A UI component that displays information of a {@code Student}.
 */
public class StudentView extends UiPart<Region> {
    private static final String FXML = "StudentView.fxml";

    /**
     * Creates a {@code StudentView}.
     */
    public StudentView() {
        super(FXML);
    }
}
