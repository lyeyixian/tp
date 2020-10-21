package seedu.address.model;


import javafx.collections.ObservableList;
import seedu.address.model.activity.Activity;

/**
 * Represents the current directory of wanderlust, which can be instance of TravelPlan or Wishlist
 */
public abstract class Directory {
    public abstract ObservableList<Activity> getActivityList();

}
