package seedu.address.logic.commands.delete;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.travelplanner.Model;
import seedu.address.model.travelplan.TravelPlan;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteTravelPlanCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "travelplan";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the travel plan identified by the index number used in the displayed travel planner list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TRAVELPLAN_SUCCESS = "Deleted Travel Plan: %1$s";

    private final Index targetIndex;

    public DeleteTravelPlanCommand(Index targetIndex) {
        super(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TravelPlan> lastShownList = model.getFilteredTravelPlanList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRAVELPLAN_DISPLAYED_INDEX);
        }

        TravelPlan travelPlanToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTravelPlan(travelPlanToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TRAVELPLAN_SUCCESS, travelPlanToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTravelPlanCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTravelPlanCommand) other).targetIndex)); // state check
    }
}
