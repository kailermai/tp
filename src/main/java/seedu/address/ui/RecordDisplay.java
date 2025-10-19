package seedu.address.ui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Window;
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.SubmissionScore;
import seedu.address.model.record.WeekNumber;

/**
 * A UI component that displays the scores of a student in the form of a horizontal bar.
 */
public class RecordDisplay extends HBox {

    private static final int BOX_WIDTH = 45;
    private static final int BOX_HEIGHT = 36;
    private static final int STROKE_WIDTH = 1;
    private static final int BORDER_RADIUS = 8;

    // maximum dimensions for each individual box
    private static final double MAX_BOX_WIDTH = 45;
    private static final double MAX_BOX_HEIGHT = 36;

    private static final Color COLOR_BORDER = Color.DIMGRAY;
    private static final Color COLOR_DEFAULT = Color.DARKGRAY;
    private static final Color COLOR_SCORE_HIGH = Color.SEAGREEN;
    private static final Color COLOR_SCORE_MEDIUM = Color.ORANGE;
    private static final Color COLOR_SCORE_LOW = Color.TOMATO;

    private final StackPane[] scoreBoxes;

    /**
     * Creates a {@code RecordDisplay} made up of {@code StackPanes}.
     * The number of {@code StackPanes} is equal to the number of weeks in a typical semester,
     * as defined by {@code WeekNumber.MAX_WEEK_NUMBER}.
     * Each {@code StackPane} contains a {@code Rectangle} and a {@code Label}.
     * The {@code Rectangle} is colored according to the score, and the {@code Label} displays the score.
     */
    public RecordDisplay() {
        this.scoreBoxes = new StackPane[WeekNumber.MAX_WEEK_NUMBER];

        // Disable caching on this control to avoid stale rendering after resize
        setCache(false);
        // allow children to be resized to fill the HBox vertically
        setFillHeight(true);

        for (int i = 0; i < scoreBoxes.length; i++) {
            Label scoreLabel = new Label("");

            Rectangle box = new Rectangle(BOX_WIDTH, BOX_HEIGHT, COLOR_DEFAULT);
            box.setArcHeight(BORDER_RADIUS);
            box.setArcWidth(BORDER_RADIUS);
            box.setStroke(COLOR_BORDER);
            box.setStrokeWidth(STROKE_WIDTH);

            // disable caching on rectangles and panes
            box.setSmooth(true);
            box.setCache(false);

            StackPane boxPane = new StackPane(box, scoreLabel);
            // enforce per-box limits so boxes won't grow beyond these values
            boxPane.setMaxWidth(MAX_BOX_WIDTH);
            boxPane.setMaxHeight(MAX_BOX_HEIGHT);
            StackPane.setAlignment(scoreLabel, Pos.CENTER);
            // allow each box to grow/shrink horizontally
            HBox.setHgrow(boxPane, Priority.ALWAYS);

            // bind rectangle size to container so it resizes with window
            box.widthProperty().bind(boxPane.widthProperty().subtract(4)); // leave small padding
            box.heightProperty().bind(boxPane.heightProperty().subtract(4)); // now responsive to height

            scoreBoxes[i] = boxPane;
            this.getChildren().add(boxPane);
        }

        // When this control changes size, force a relayout/applyCss on the UI thread
        this.widthProperty().addListener((obs, oldV, newV) -> forceLayoutLater());
        this.heightProperty().addListener((obs, oldV, newV) -> forceLayoutLater());

        // Listen for scene/window changes to catch top-level resize events and force layout
        this.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene == null) {
                return;
            }

            // handle when window is already set
            Window window = newScene.getWindow();
            if (window != null) {
                attachWindowListeners(window);
            }

            // handle window being set later
            newScene.windowProperty().addListener((o, oldW, newW) -> {
                if (newW != null) {
                    attachWindowListeners(newW);
                }
            });
        });
    }

    private void attachWindowListeners(Window window) {
        // ensure we don't add duplicate listeners aggressively; cheap listeners are OK here
        window.widthProperty().addListener((o, ov, nv) -> forceLayoutLater());
        window.heightProperty().addListener((o, ov, nv) -> forceLayoutLater());
    }

    private void forceLayoutLater() {
        Platform.runLater(() -> {
            try {
                applyCss();
                requestLayout();
                layout();

                if (getScene() != null && getScene().getRoot() != null) {
                    getScene().getRoot().applyCss();
                    getScene().getRoot().requestLayout();
                    getScene().getRoot().layout();
                }

                // also request layout for child panes to ensure bindings take effect immediately
                for (StackPane sp : scoreBoxes) {
                    if (sp != null) {
                        sp.applyCss();
                        sp.requestLayout();
                        sp.layout();
                    }
                }
            } catch (Exception ignored) {
                // defensive: avoid throwing during resize callbacks
            }
        });
    }

    /**
     * Represents the type of score being displayed.
     */
    public enum ScoreType {
        ATTENDANCE, SUBMISSION, PARTICIPATION
    }

    /**
     * Displays the scores in the {@code RecordDisplay}.
     * Each score is represented by a colored box and a label displaying the score value.
     *
     * @param scores An array of {@code Score} objects to be displayed.
     */
    public void displayScores(Integer[] scores, ScoreType scoreType) {
        assert scores.length == scoreBoxes.length;

        for (int i = 0; i < scoreBoxes.length; i++) {
            StackPane boxPane = scoreBoxes[i];
            Rectangle box = (Rectangle) boxPane.getChildren().get(0);
            Label scoreLabel = (Label) boxPane.getChildren().get(1);

            Integer score = scores[i];
            box.setFill(setBoxColor(score, scoreType));
            scoreLabel.setText(score != null ? String.valueOf(score) : "");
        }

        // ensure updated fills/labels are rendered immediately
        forceLayoutLater();
    }

    private Color setBoxColor(Integer score, ScoreType scoreType) {
        if (score == null) {
            return COLOR_DEFAULT;
        }

        switch (scoreType) {
        case ATTENDANCE:
            return score == AttendanceScore.MAX_SCORE ? COLOR_SCORE_HIGH : COLOR_SCORE_LOW;
        case SUBMISSION:
            return score == SubmissionScore.MAX_SCORE ? COLOR_SCORE_HIGH : COLOR_SCORE_LOW;
        case PARTICIPATION:
            if (score == ParticipationScore.MAX_SCORE) {
                return COLOR_SCORE_HIGH;
            } else if (score == ParticipationScore.MIN_SCORE) {
                return COLOR_SCORE_LOW;
            } else {
                return COLOR_SCORE_MEDIUM;
            }
        default:
            return COLOR_DEFAULT;
        }
    }
}
