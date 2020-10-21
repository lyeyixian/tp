package seedu.address.logic.command.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.Importance;
import seedu.address.model.activity.WanderlustDateTime;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;


/**
 * Edits an existing Activity in the address book and updates the travel plan/wishlist in the current directory
 * An activity contains the field name, location, cost, level of importance and date time
 */
public class EditActivityCommand extends EditCommand {
    public static final String COMMAND_WORD = "activity";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the activity identified by the index number used in the displayed activity list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_COST + "COST] "
            + "[" + PREFIX_IMPORTANCE + "IMPORTANCE] "
            + "[" + PREFIX_DATETIME + "DATETIME]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Ice Fishing "
            + PREFIX_IMPORTANCE + "2 "
            + PREFIX_LOCATION + "Ice Park "
            + PREFIX_COST + "50 "
            + PREFIX_DATETIME + " 2020-05-05 14:30";

    public static final String MESSAGE_EDIT_ACTIVITY_SUCCESS = "Edited Activity: %1$s";
    public static final String MESSAGE_DUPLICATE_ACTIVITY = "This activity already exists in the activity list.";

    private final Index targetIndex;
    private final EditDescriptor editActivityDescriptor;

    /**
     * Constructor for edit activity command
     */
    public EditActivityCommand(Index targetIndex, EditDescriptor editActivityDescriptor) {
        super(targetIndex);
        this.targetIndex = targetIndex;
        this.editActivityDescriptor = editActivityDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isTravelPlan = model.isDirectoryTypeTravelPlan();
        if (isTravelPlan) {
            List<Activity> lastShownList = model.getFilteredActivityList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
            }

            Activity activityToEdit = lastShownList.get(targetIndex.getZeroBased());
            Activity editedActivity = createEditedActivity(activityToEdit, editActivityDescriptor);

            if (!activityToEdit.isSameActivity(editedActivity) && model.hasActivity(editedActivity)) {
                throw new CommandException(MESSAGE_DUPLICATE_ACTIVITY);
            }

            model.setTravelPlanObject(activityToEdit, editedActivity);
            return new CommandResult(String.format(MESSAGE_EDIT_ACTIVITY_SUCCESS, editedActivity));
        } else {
            List<Activity> lastShownList = model.getFilteredWishlist();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
            }

            Activity activityToEdit = lastShownList.get(targetIndex.getZeroBased());
            Activity editedActivity = createEditedActivity(activityToEdit, editActivityDescriptor);

            if (!activityToEdit.isSameActivity(editedActivity) && model.hasActivity(editedActivity)) {
                throw new CommandException(MESSAGE_DUPLICATE_ACTIVITY);
            }

            model.setActivity(activityToEdit, editedActivity);
            return new CommandResult(String.format(MESSAGE_EDIT_ACTIVITY_SUCCESS, editedActivity));
        }
    }

    /**
     * Creates and returns a {@code Activity} with the details of {@code activityToEdit}
     *
     * @param activityToEdit         contains the old fields
     * @param editActivityDescriptor contains updated fields
     * @return Activity to be updated in the activity list
     */
    private static Activity createEditedActivity(Activity activityToEdit,
                                                 EditDescriptor editActivityDescriptor) {
        assert activityToEdit != null;

        Name updatedName = editActivityDescriptor.getName().orElse(activityToEdit.getName());

        Location updatedlocation = editActivityDescriptor.getLocation().orElse(activityToEdit.getLocation());
        Cost updatedCost = editActivityDescriptor.getCost().orElse(activityToEdit.getCost());
        Importance updatedLevelOfImportance = editActivityDescriptor.getLevelOfImportance()
                .orElse(activityToEdit.getLevelOfImportance());
        WanderlustDateTime updatedActivityDateTime = editActivityDescriptor.getActivityDateTime()
                .orElse(activityToEdit.getActivityDateTime());

        return new Activity(updatedName, updatedlocation, updatedCost, updatedLevelOfImportance,
                updatedActivityDateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommand // instanceof handles nulls
                && targetIndex.equals(((EditActivityCommand) other).targetIndex)) // state check
                && (editActivityDescriptor.equals(((EditActivityCommand) other).editActivityDescriptor)
                || editActivityDescriptor.isSameDescriptor(((EditActivityCommand) other).editActivityDescriptor));
    }

}
