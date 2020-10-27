package seedu.address.model.activity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Activity's date time in a travel plan.
 * Guarantees: immutable; is valid as declared in {@link #isValidWanderlustDateTime(String)}
 */
public class WanderlustDateTime {
    public static final String MESSAGE_CONSTRAINTS = "Date Time should be of the format YYYY-MM-DD HH:mm.";

    /**
     * Date time must be in the format YYYY-MM-DD HH:mm.
     */
    public static final DateFormat VALID_DATE_STRING = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public final String dateTime;
    private final LocalDateTime value;

    /**
     * Constructs a {@code WanderlustDateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public WanderlustDateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidWanderlustDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = dateTime;
        value = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    }

    /**
     * Returns if a given string is a valid activity date time.
     */
    public static boolean isValidWanderlustDateTime(String test) {
        try {
            VALID_DATE_STRING.parse(test);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WanderlustDateTime // instanceof handles nulls
                && dateTime.equals(((WanderlustDateTime) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
