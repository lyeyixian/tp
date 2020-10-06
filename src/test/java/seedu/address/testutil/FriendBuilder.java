package seedu.address.testutil;

import seedu.address.model.commons.Name;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.Passport;
import seedu.address.model.friend.Phone;

/**
 * A utility class to help with building Friend objects.
 */
public class FriendBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PASSPORT = "M1234567";
    public static final String DEFAULT_PHONE = "85355255";

    private Name name;
    private Passport passport;
    private Phone phone;

    /**
     * Creates a {@code FriendBuilder} with the default details.
     */
    public FriendBuilder() {
        name = new Name(DEFAULT_NAME);
        passport = new Passport(DEFAULT_PASSPORT);
        phone = new Phone(DEFAULT_PHONE);
    }

    /**
     * Initializes the FriendBuilder with the data of {@code friendToCopy}.
     */
    public FriendBuilder(Friend friendToCopy) {
        name = friendToCopy.getName();
        passport = friendToCopy.getPassport();
        phone = friendToCopy.getPhone();
    }

    /**
     * Sets the {@code Name} of the {@code Friend} that we are building.
     */
    public FriendBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Friend} that we are building.
     */
    public FriendBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Passport} of the {@code Friend} that we are building.
     */
    public FriendBuilder withPassport(String passport) {
        this.passport = new Passport(passport);
        return this;
    }

    public Friend build() {
        return new Friend(name, passport, phone);
    }
}