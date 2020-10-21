package seedu.address.logic.command.delete;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.TravelPlanObject;

/**
 * Deletes an accommodation in a travel plan identified using the index from the travel plan.
 */
public class DeleteAccommodationCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "accommodation";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the accommodation identified by the index number used in the displayed accommodation list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ACCOMMODATION_SUCCESS = "Deleted Accommodation: %1$s";

    private final Index targetIndex;

    /**
     * Constructor for Delete Accommodation Command
     * @param targetIndex index to be deleted
     */
    public DeleteAccommodationCommand(Index targetIndex) {
        super(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<? extends TravelPlanObject> filteredAccommodationList = model.getFilteredAccommodationList();

        if (targetIndex.getZeroBased() >= filteredAccommodationList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACCOMMODATION_DISPLAYED_INDEX);
        }

        TravelPlanObject accommodationToDelete = filteredAccommodationList.get(targetIndex.getZeroBased());

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
