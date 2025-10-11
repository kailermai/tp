package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.address.model.record.Record;
/**
 * An UI component that displays information of a {@code Record}.
 */
public class ScoreCard extends UiPart<Region> {

    private static final String FXML = "ScoreCard.fxml";

    public final Record record;

    @FXML
    private HBox cardPane;
    @FXML
    private Label weekNumber;
    @FXML
    private Label recordImage;

    /**
     * Creates a {@code ScoreCard} with the given {@code Record} to display.
     */
    public ScoreCard(Record record, int weekNumber) {
        super(FXML);
        this.record = record;
        this.weekNumber.setText("Week " + weekNumber);
        this.recordImage.setText(record.toString());
    }

}
