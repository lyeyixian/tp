package seedu.address.logic.command.sort;

import seedu.address.logic.command.Command;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.ShowCommand;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;

public abstract class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String KEYWORD_NAME = "name";
    public static final String KEYWORD_COST = "cost";
    public static final String KEYWORD_IMPORTANCE = "importance";
    public static final String KEYWORD_DATE = "date";
    public static final String KEYWORD_DATETIME = "date and time";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": sort the respective list based on the keyword input.\n"
            + "Parameters: travelPlanObjectType, sort keywords\n"
            + "Example: " + COMMAND_WORD + " -activity cost";

    private final String sortKeyword;

    /**
     * Constructor for ShowCommand.
     * @param sortKeyword string of the keyword used to sort.
     */
    public SortCommand(String sortKeyword) {
        this.sortKeyword = sortKeyword;
    }

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && sortKeyword.equals(((SortCommand) other).sortKeyword));
    }
}
