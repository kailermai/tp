package seedu.address.ui;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
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
    private static final double MIN_BOX_WIDTH = 10;
    private static final double MIN_BOX_HEIGHT = 8;

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
            // enforce hard min/max values
            boxPane.setMinWidth(MIN_BOX_WIDTH);
            boxPane.setMaxWidth(MAX_BOX_WIDTH);
            boxPane.setMinHeight(MIN_BOX_HEIGHT);
            boxPane.setMaxHeight(MAX_BOX_HEIGHT);
            StackPane.setAlignment(scoreLabel, Pos.CENTER);
            // allow each box to grow/shrink horizontally
            HBox.setHgrow(boxPane, Priority.ALWAYS);

            // compute an even per-box preferred width based on this HBox's width,
            // respecting spacing and capping by MAX_BOX_WIDTH
            DoubleBinding perBoxPref = Bindings.createDoubleBinding(() -> {
                double totalSpacing = Math.max(0, (scoreBoxes.length - 1) * getSpacing());
                double avail = Math.max(0, getWidth() - totalSpacing);
                double per = scoreBoxes.length > 0 ? avail / scoreBoxes.length : 0;
                per = Math.max(MIN_BOX_WIDTH, Math.min(MAX_BOX_WIDTH, per));
                return per;
            }, widthProperty(), spacingProperty());

            boxPane.prefWidthProperty().bind(perBoxPref);

            // bind rectangle size safely (never negative)
            DoubleBinding rectW = Bindings.createDoubleBinding(
                    () -> Math.max(0, boxPane.getWidth() - 4),
                    boxPane.widthProperty());
            DoubleBinding rectH = Bindings.createDoubleBinding(
                    () -> Math.max(0, boxPane.getHeight() - 4),
                    boxPane.heightProperty());

            box.widthProperty().bind(rectW);
            box.heightProperty().bind(rectH);

            scoreBoxes[i] = boxPane;
            this.getChildren().add(boxPane);
        }

        // request layout on resize (do not call layout()/applyCss() synchronously)
        this.widthProperty().addListener((obs, oldV, newV) -> scheduleLayout());
        this.heightProperty().addListener((obs, oldV, newV) -> scheduleLayout());

        this.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene == null) {
                return;
            }
            Window window = newScene.getWindow();
            if (window != null) {
                attachWindowListeners(window);
            }
            newScene.windowProperty().addListener((o, oldW, newW) -> {
                if (newW != null) {
                    attachWindowListeners(newW);
                }
            });
        });
    }

    private void attachWindowListeners(Window window) {
        window.widthProperty().addListener((o, ov, nv) -> scheduleLayout());
        window.heightProperty().addListener((o, ov, nv) -> scheduleLayout());
    }

    private void scheduleLayout() {
        Platform.runLater(() -> {
            try {
                requestLayout();
                if (getScene() != null && getScene().getRoot() != null) {
                    getScene().getRoot().requestLayout();
                }
                for (StackPane sp : scoreBoxes) {
                    if (sp != null) {
                        sp.requestLayout();
                    }
                }
            } catch (Exception ignored) {
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
        scheduleLayout();
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
