package seedu.address.logic.command.delete;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.activity.Activity;
import seedu.address.model.commons.TravelPlanObject;

/**
 * Deletes an activity in a travel plan or wishlist identified using the index from the travel plan or wishlist.
 */
public class DeleteActivityCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "activity";

    public static final String MESSAGE_USAGE =
            "delete: Deletes the activity identified by the index number used in the displayed travel plan list.\n"
            + "Parameters: INDEX (must be a positive integer)\n";

    public static final String MESSAGE_DELETE_ACTIVITY_SUCCESS = "Deleted Activity: %1$s";

    private final Index targetIndex;

    /**
     * Constructor for DeleteActivityCommand.
     * @param targetIndex index to be deleted.
     */
    public DeleteActivityCommand(Index targetIndex) {
        super(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isTravelPlan = model.isDirectoryTypeTravelPlan();
        if (isTravelPlan) {
            List<? extends TravelPlanObject> filteredActivityList = model.getFilteredActivityList();

            if (targetIndex.getZeroBased() >= filteredActivityList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
            }

            TravelPlanObject activityToDelete = filteredActivityList.get(targetIndex.getZeroBased());

            model.deleteTravelPlanObject(activityToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete));
        } else {
            List<Activity> filteredWishList = model.getFilteredWishlist();

            if (targetIndex.getZeroBased() >= filteredWishList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
            }

            Activity activityToDelete = filteredWishList.get(targetIndex.getZeroBased());

            model.deleteActivity(activityToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteActivityCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteActivityCommand) other).targetIndex)); // state check
    }
}
