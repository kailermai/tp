package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RecordCommand;
import seedu.address.logic.commands.TrendCommand;
import seedu.address.logic.commands.ViewCommand;

/**
 * Controller for a help page
 */
public class HelpPanel extends UiPart<Region> {

    public static final String USERGUIDE_URL = "https://ay2526s1-cs2103t-t16-2.github.io/tp/UserGuide.html";
    public static final String USERGUIDE_TEXT = "More can be found in our user guide: " + USERGUIDE_URL;

    public static final String HELP_MESSAGE = "Command list: \n"
            + HelpCommand.MESSAGE_HELP + "\n\n"
            + AddCommand.MESSAGE_HELP + "\n\n"
            + ListCommand.MESSAGE_HELP + "\n\n"
            + EditCommand.MESSAGE_HELP + "\n\n"
            + FindCommand.MESSAGE_HELP + "\n\n"
            + DeleteCommand.MESSAGE_HELP + "\n\n"
            + RecordCommand.MESSAGE_HELP + "\n\n"
            + ClearCommand.MESSAGE_HELP + "\n\n"
            + ExitCommand.MESSAGE_HELP + "\n\n"
            + ViewCommand.MESSAGE_HELP + "\n\n"
            + TrendCommand.MESSAGE_HELP + "\n\n\n"
            + USERGUIDE_TEXT;

    private static final String FXML = "HelpPanel.fxml";

    @FXML
    private ScrollPane helpScrollPane;

    @FXML
    private Label helpMessage;

    @FXML
    private Button copyButton;

    /**
     * Creates a HelpPanel.
     */
    public HelpPanel() {
        super(FXML);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Initializes the help panel.
     */
    @FXML
    public void initialize() {
        if (helpScrollPane != null) {
            enableFastScroll(helpScrollPane, 0.2);
        }
    }

    /**
     * Configures a ScrollPane for faster vertical-wheel scrolling.
     * @param scrollPane the ScrollPane to be configured
     * @param scrollStep the fraction of total height to scroll per wheel notch. (0.2 = 20%)
     */
    private static void enableFastScroll(ScrollPane scrollPane, double scrollStep) {
        scrollPane.setFitToWidth(true);

        // Speed up vertical-wheel scrolling
        scrollPane.addEventFilter(ScrollEvent.SCROLL, e -> {
            if (e.isControlDown() || e.getDeltaY() == 0) {
                return;
            }
            if (Math.abs(e.getDeltaX()) > Math.abs(e.getDeltaY())) {
                return;
            }
            double step = (e.getDeltaY() > 0 ? -scrollStep : scrollStep);
            double v = Math.max(0, Math.min(1, scrollPane.getVvalue() + step));
            scrollPane.setVvalue(v);
            e.consume();
        });
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final javafx.scene.input.Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
