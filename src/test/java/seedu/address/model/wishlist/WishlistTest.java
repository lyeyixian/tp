package seedu.address.model.wishlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalActivities.ARCHERY;
import static seedu.address.testutil.typicals.TypicalActivities.getTypicalWishlist;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.exceptions.DuplicateActivityException;
import seedu.address.testutil.builders.ActivityBuilder;

public class WishlistTest {

    private final Wishlist wishlist = new Wishlist();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), wishlist.getObservableActivityList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wishlist.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWishlist_replacesData() {
        Wishlist newData = getTypicalWishlist();
        wishlist.resetData(newData);
        assertEquals(newData, wishlist);
    }

    @Test
    public void resetData_withDuplicateActivities_throwsDuplicateActivityException() {
        // Two activities with the same identity fields
        Activity editedZoo = new ActivityBuilder(ARCHERY).build();
        List<Activity> newActivities = Arrays.asList(ARCHERY, editedZoo);
        WishlistStub newData = new WishlistStub(newActivities);

        assertThrows(DuplicateActivityException.class, () -> wishlist.resetData(newData));
    }

    @Test
    public void hasActivity_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wishlist.hasActivity(null));
    }

    @Test
    public void hasActivity_activityNotInWishlist_returnsFalse() {
        assertFalse(wishlist.hasActivity(ARCHERY));
    }

    @Test
    public void hasActivity_activityInWishlist_returnsTrue() {
        wishlist.addActivity(ARCHERY);
        assertTrue(wishlist.hasActivity(ARCHERY));
    }

    @Test
    public void getWishlist_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> wishlist.getObservableActivityList().remove(0));
    }

    /**
     * TODO
     * A stub ReadOnlyWishlist whose activities list can violate interface constraints.
     */
    private static class WishlistStub extends Wishlist {
        private final ObservableList<Activity> activities = FXCollections.observableArrayList();

        WishlistStub(Collection<Activity> activities) {
            this.activities.setAll(activities);
        }

        public ObservableList<Activity> getObservableActivityList() {
            return activities;
        }
    }

}
