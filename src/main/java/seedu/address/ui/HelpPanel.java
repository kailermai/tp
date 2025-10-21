package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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

    private static final String FXML = "HelpPanel.fxml";

    @FXML
    private ScrollPane helpScrollPane;

    @FXML
    private VBox helpMessage;

    @FXML
    private Button copyButton;

    /**
     * Creates a HelpPanel.
     */
    public HelpPanel() {
        super(FXML);
        buildHelpMessage();
    }

    private void buildHelpMessage() {
        helpMessage.getChildren().addAll(
            createHeader("Command list:"),
            createCommandTitleAndDescription(HelpCommand.MESSAGE_HELP_TITLE, HelpCommand.MESSAGE_HELP_DESCRIPTION),
            createCommandTitleAndDescription(AddCommand.MESSAGE_HELP_TITLE, AddCommand.MESSAGE_HELP_DESCRIPTION),
            createCommandTitleAndDescription(ListCommand.MESSAGE_HELP_TITLE, ListCommand.MESSAGE_HELP_DESCRIPTION),
            createCommandTitleAndDescription(EditCommand.MESSAGE_HELP_TITLE, EditCommand.MESSAGE_HELP_DESCRIPTION),
            createCommandTitleAndDescription(FindCommand.MESSAGE_HELP_TITLE, FindCommand.MESSAGE_HELP_DESCRIPTION),
            createCommandTitleAndDescription(DeleteCommand.MESSAGE_HELP_TITLE, DeleteCommand.MESSAGE_HELP_DESCRIPTION),
            createCommandTitleAndDescription(RecordCommand.MESSAGE_HELP_TITLE, RecordCommand.MESSAGE_HELP_DESCRIPTION),
            createCommandTitleAndDescription(ClearCommand.MESSAGE_HELP_TITLE, ClearCommand.MESSAGE_HELP_DESCRIPTION),
            createCommandTitleAndDescription(ExitCommand.MESSAGE_HELP_TITLE, ExitCommand.MESSAGE_HELP_DESCRIPTION),
            createCommandTitleAndDescription(ViewCommand.MESSAGE_HELP_TITLE, ViewCommand.MESSAGE_HELP_DESCRIPTION),
            createCommandTitleAndDescription(TrendCommand.MESSAGE_HELP_TITLE, TrendCommand.MESSAGE_HELP_DESCRIPTION),
            createFooter("\n" + USERGUIDE_TEXT)
        );
    }

    /**
     * Creates a header label.
     * @param text The text to be displayed in the header.
     * @return A Label object containing the header text.
     */
    private Label createHeader(String text) {
        Label header = new Label(text);
        header.getStyleClass().add("help-header");
        header.setWrapText(true);
        return header;
    }

    /**
     * Creates a command title and description block.
     * @param title The title of the command.
     * @param description The description of the command.
     * @return A VBox object containing the command title and description.
     */
    private VBox createCommandTitleAndDescription(String title, String description) {
        Label titleLabel = createCommandTitle(title);
        Label descriptionLabel = createCommandDescription(description);

        VBox command = new VBox(2, titleLabel, descriptionLabel);
        command.getStyleClass().add("help-command-block");
        return command;
    }

    /**
     * Creates a command title label.
     * @param text The text to be displayed in the title.
     * @return A Label object containing the command title.
     */
    private Label createCommandTitle(String text) {
        Label command = new Label(text);
        command.getStyleClass().add("help-command-title");
        command.setWrapText(true);
        return command;
    }

    /**
     * Creates a command description label.
     * @param text The text to be displayed in the description.
     * @return A Label object containing the command description.
     */
    private Label createCommandDescription(String text) {
        Label command = new Label(text);
        command.getStyleClass().add("help-command-description");
        command.setWrapText(true);
        return command;
    }

    /**
     * Creates a footer label.
     * @param text The text to be displayed in the footer.
     * @return A Label object containing the footer text.
     */
    private Label createFooter(String text) {
        Label footer = new Label(text);
        footer.getStyleClass().add("help-footer");
        footer.setWrapText(true);
        return footer;
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
