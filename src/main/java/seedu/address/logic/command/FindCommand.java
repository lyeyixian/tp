package seedu.address.logic.command;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ACCOMMODATIONS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_FRIENDS_LISTED_OVERVIEW;

import seedu.address.model.Model;
import seedu.address.model.commons.NameContainsKeywordsPredicate;

/**
 * Finds and lists all travel plan object in Wanderlust whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all travel plan object "
            + "whose names contain any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: \n"
            + "Finds activities in the current travel plan or wishlist using the format:\n"
            + "find -activity KEYWORD [MORE_KEYWORDS]...\n"
            + "Finds accommodations in the current travel plan using the format:\n"
            + "find -accommodation KEYWORD [MORE_KEYWORDS]...\n"
            + "Finds friends in the current travel plan using the format:\n"
            + "find -friend KEYWORD [MORE_KEYWORDS]...\n";

    public static final String MISSING_KEYWORDS = "Please provide at least one keyword";


    private final NameContainsKeywordsPredicate predicate;
    private final int travelPlanObjectType;

    /**
     * Constructor for FindCommand
     *
     * @param predicate contains a list of strings to filter travel plan object list
     * @param travelPlanObjectType an integer to identify the type of travel plan object to find
     */
    public FindCommand(NameContainsKeywordsPredicate predicate, int travelPlanObjectType) {
        this.predicate = predicate;
        this.travelPlanObjectType = travelPlanObjectType;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        String message = "";
        int size = 0;

        if (this.travelPlanObjectType == 0) { //activity
            model.updateFilteredActivityList(predicate);
            message = MESSAGE_ACTIVITIES_LISTED_OVERVIEW;
            size = model.getFilteredActivityList().size();

        } else if (this.travelPlanObjectType == 1) { //accommodation
            model.updateFilteredAccommodationList(predicate);
            message = MESSAGE_ACCOMMODATIONS_LISTED_OVERVIEW;
            size = model.getFilteredAccommodationList().size();


        } else if (this.travelPlanObjectType == 2) { //friend
            model.updateFilteredFriendList(predicate);
            message = MESSAGE_FRIENDS_LISTED_OVERVIEW;
            size = model.getFilteredFriendList().size();

        }

        return new CommandResult(
                String.format(message, size), this.travelPlanObjectType);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && travelPlanObjectType == (((FindCommand) other).travelPlanObjectType)
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
