package seedu.address.logic.command.edit;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.Command;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Edits a person identified using it's displayed index from the address book.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String SPECIFY_INDEX = "Please Specify the index";
    public static final String MESSAGE_WRONG_DIRECTORY = "Please go to a travelplan directory to use this command";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " with optional fields\n"
            + "Edits a travel plan to the travel planner using the format:\n"
            + "edit -travelplan n/NAME sd/START_DATE ed/END_DATE\n"
            + "Edits an activity to the current travel plan using the format:\n"
            + "edit -activity n/NAME i/LEVEL_OF_IMPORTANCE l/LOCATION c/COST d/DATE_AND_TIME\n"
            + "Edits an accommodation to the current travel plan using the format:\n"
            + "edit -accommodation n/NAME l/LOCATION c/COST sd/START_DATE ed/END_DATE\n"
            + "Edits a friend to the current travel plan using the format:\n"
            + "edit -friend n/NAME m/MOBILE_NUMBER p/PASSPORT_NUMBER\n";


    protected final Index targetIndex;

    public EditCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommand // instanceof handles nulls
                && targetIndex.equals(((EditCommand) other).targetIndex)); // state check
    }
}
