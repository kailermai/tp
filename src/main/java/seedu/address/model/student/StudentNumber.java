package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's student number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentNumber(String)}
 */
public class StudentNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "Student numbers should be in the following format: AXXXXXXXZ, where A is the character 'A', X is a "
                    + "digit from 0 to 9, and Z is any letter. Letters are case-insensitive.";
    public static final String VALIDATION_REGEX = "[aA]\\d{7}[a-zA-Z]";
    public final String value;

    /**
     * Constructs a {@code StudentNumber}.
     *
     * @param studentNumber A valid student number.
     */
    public StudentNumber(String studentNumber) {
        requireNonNull(studentNumber);
        checkArgument(isValidStudentNumber(studentNumber), MESSAGE_CONSTRAINTS);
        value = studentNumber.toUpperCase();
    }

    public static boolean isValidStudentNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentNumber)) {
            return false;
        }

        StudentNumber otherStudentNumber = (StudentNumber) other;
        return value.equals(otherStudentNumber.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
