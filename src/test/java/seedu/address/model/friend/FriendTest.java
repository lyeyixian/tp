package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.command.CommandTestUtil.VALID_MOBILE_BOB;
import static seedu.address.logic.command.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.command.CommandTestUtil.VALID_PASSPORT_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.builders.FriendBuilder;
import seedu.address.testutil.typicals.TypicalFriends;

class FriendTest {

    // should return true if identity field(name) is the same
    @Test
    public void isSameFriend() {
        // same object -> returns true
        assertTrue(TypicalFriends.ALICE.isSameFriend(TypicalFriends.ALICE));

        // null -> returns false
        assertFalse(TypicalFriends.ALICE.isSameFriend(null));
        Friend editedAlice;

        // different name -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(TypicalFriends.ALICE.isSameFriend(editedAlice));

        // same name with different mobile and passport -> returns true
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withMobile(VALID_MOBILE_BOB)
                .withPassport(VALID_PASSPORT_BOB).build();

        assertTrue(TypicalFriends.ALICE.isSameFriend(editedAlice));

        // same name, same passport with different mobile -> returns true
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withMobile(VALID_MOBILE_BOB).build();

        assertTrue(TypicalFriends.ALICE.isSameFriend(editedAlice));

        // same name, same mobile, different passport -> returns true
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withPassport(VALID_PASSPORT_BOB).build();

        assertTrue(TypicalFriends.ALICE.isSameFriend(editedAlice));

        // same name, same mobile, same passport -> returns true
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).build();
        assertTrue(TypicalFriends.ALICE.isSameFriend(editedAlice));
    }

    //test if two friends have the same identity and data fields
    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(TypicalFriends.ALICE.equals(TypicalFriends.ALICE));

        // null -> returns false
        assertFalse(TypicalFriends.ALICE.equals(null));
        Friend editedAlice;

        // different name -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(TypicalFriends.ALICE.equals(editedAlice));

        // same name with different mobile and passport -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withMobile(VALID_MOBILE_BOB)
                .withPassport(VALID_PASSPORT_BOB).build();

        assertFalse(TypicalFriends.ALICE.equals(editedAlice));

        // same name, same passport with different mobile -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withMobile(VALID_MOBILE_BOB).build();

        assertFalse(TypicalFriends.ALICE.equals(editedAlice));

        // same name, same mobile, different passport -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withPassport(VALID_PASSPORT_BOB).build();

        assertFalse(TypicalFriends.ALICE.equals(editedAlice));

        // same name, same mobile, same passport -> returns true
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).build();
        assertTrue(TypicalFriends.ALICE.equals(editedAlice));

    }

}
