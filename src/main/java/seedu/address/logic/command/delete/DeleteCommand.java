package seedu.address.logic.command.delete;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.Command;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a travel plan object identified using it's displayed index from the travel plans.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the activity identified by the index number used in the displayed travel plan list.\n"
            + "Parameters: INDEX (must be a positive integer)\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    protected final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
