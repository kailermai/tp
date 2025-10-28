package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's Telegram handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {
    public static final String MESSAGE_CONSTRAINTS =
            "Telegram should contain only alphanumeric characters and underscores, must not be blank, "
            + "and should be no more than 100 characters long";

    public static final String VALIDATION_REGEX = "[a-zA-Z0-9_]{1,100}";

    public final String value;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param telegram A valid telegram handle.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
        this.value = telegram;
    }

    public static boolean isValidTelegram(String test) {
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
        if (!(other instanceof Telegram)) {
            return false;
        }

        Telegram otherTelegram = (Telegram) other;
        return value.equals(otherTelegram.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
