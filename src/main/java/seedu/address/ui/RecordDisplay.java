package seedu.address.ui;


import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.Score;
import seedu.address.model.record.SubmissionScore;
import seedu.address.model.record.WeekNumber;

/**
* A UI component that displays the scores of a student in the form of a horizontal bar.
*/
public class RecordDisplay extends HBox {

    private static final int BOX_WIDTH = 30;
    private static final int BOX_HEIGHT = 20;

    private static final Color COLOR_DEFAULT = Color.LIGHTGRAY;
    private static final Color COLOR_SCORE_HIGH = Color.LIMEGREEN;
    private static final Color COLOR_SCORE_MEDIUM = Color.YELLOWGREEN;
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

        for (int i = 0; i < WeekNumber.MAX_WEEK_NUMBER; i++) {
            Label scoreLabel = new Label("");
            Rectangle box = new Rectangle(BOX_WIDTH, BOX_HEIGHT, COLOR_DEFAULT);
            StackPane boxPane = new StackPane(box, scoreLabel);
            scoreBoxes[i] = boxPane;

            this.getChildren().add(boxPane);
        }
    }

    /**
     * Displays the scores in the {@code RecordDisplay}.
     * Each score is represented by a colored box and a label displaying the score value.
     *
     * @param scores An array of {@code Score} objects to be displayed.
     */
    public void displayScores(Score[] scores) {

        assert scores.length == scoreBoxes.length;

        for (int i = 0; i < scoreBoxes.length; i++) {

            StackPane boxPane = scoreBoxes[i];
            Rectangle box = (Rectangle) boxPane.getChildren().get(0);
            Label scoreLabel = (Label) boxPane.getChildren().get(1);

            box.setFill(setBoxColor(scores[i]));
            if (scores[i] != null) {
                scoreLabel.setText(String.valueOf(scores[i].getScore()));
            }
        }
    }

    private Color setBoxColor(Score score) {
        if (score instanceof AttendanceScore) {
            return score.getScore() == AttendanceScore.MAX_SCORE ? COLOR_SCORE_HIGH : COLOR_SCORE_LOW;
        } else if (score instanceof SubmissionScore) {
            return score.getScore() == SubmissionScore.MAX_SCORE ? COLOR_SCORE_HIGH : COLOR_SCORE_LOW;
        } else if (score instanceof ParticipationScore) {
            if (score.getScore() == ParticipationScore.MAX_SCORE) {
                return COLOR_SCORE_HIGH;
            } else if (score.getScore() == ParticipationScore.MIN_SCORE) {
                return COLOR_SCORE_LOW;
            } else {
                return COLOR_SCORE_MEDIUM;
            }
        } else {
            return COLOR_DEFAULT;
        }
    }

}
