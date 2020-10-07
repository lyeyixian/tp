package seedu.address.logic.commands.delete;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.commons.TravelPlanObject;
import seedu.address.model.travelplanner.Model;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteAccommodationCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "accommodation";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the accommodation identified by the index number used in the displayed accommodation list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ACCOMMODATION_SUCCESS = "Deleted Accommodation: %1$s";

    private final Index targetIndex;

    public DeleteAccommodationCommand(Index targetIndex) {
        super(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<? extends TravelPlanObject> lastShownList = model.getFilteredTravelPlanObjectList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACCOMMODATION_DISPLAYED_INDEX);
        }

        TravelPlanObject accommodationToDelete = lastShownList.get(targetIndex.getZeroBased());

        model.deleteTravelPlanObject(accommodationToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ACCOMMODATION_SUCCESS, accommodationToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAccommodationCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteAccommodationCommand) other).targetIndex)); // state check
    }
}
