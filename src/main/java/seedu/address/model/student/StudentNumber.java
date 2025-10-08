package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

public class StudentNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "Student numbers should be in the following format: A1234567X";
    public static final String VALIDATION_regex = "[a-zA-Z]\\d{7}[a-zA-Z]";
    public final String value;

    /**
     * Constructs a {@code StudentNumber}.
     *
     * @param studentNumber A valid student number.
     */
    public StudentNumber(String studentNumber) {
        requireNonNull(studentNumber);
        value = studentNumber;
    }

    public static boolean isValidStudentNumber(String test) {
        return test.matches(VALIDATION_regex);
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
