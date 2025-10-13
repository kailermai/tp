package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_WEEK_NUMBER = new Prefix("week/");
    public static final Prefix PREFIX_ATTENDANCE_SCORE = new Prefix("att/");
    public static final Prefix PREFIX_PARTICIPATION_SCORE = new Prefix("part/");
    public static final Prefix PREFIX_SUBMISSION_SCORE = new Prefix("sub/");
    public static final Prefix PREFIX_STUDENT_NUMBER = new Prefix("sn/");
}
