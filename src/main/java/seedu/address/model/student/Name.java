package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should contain alphanumeric characters and spaces, not be blank "
                    + "and be no more than 100 characters long.\nMay include special character "
                    + "'/', as long as it is not preceded by an input prefix, eg. sn/.\n"
                    + "May also include hyphens, apostrophes, or accented characters. Non-standard formats will"
                    + " trigger a warning, but will still be accepted.";

    // Strict regex: alphanumeric, names, spaces, '/' only.
    public static final String VALIDATION_REGEX_STRICT = "(?=.{1,100}$)[\\p{Alnum}][\\p{Alnum} '/']*";

    // Lenient regex: allows accented characters, hyphens, apostrophes
    public static final String VALIDATION_REGEX_LENIENT = "(?=.{1,100}$)[\\p{L}\\p{N}'/\\-][\\p{L}\\p{N} './\\-]*";

    public final String fullName;
    private final boolean hasNonStandardCharacters;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
        hasNonStandardCharacters = !name.matches(VALIDATION_REGEX_STRICT) && name.matches(VALIDATION_REGEX_LENIENT);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX_STRICT) || test.matches(VALIDATION_REGEX_LENIENT);
    }

    public boolean hasNonStandardCharacters() {
        return hasNonStandardCharacters;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
