package seedu.address.logic.command.sort;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.accommodation.Accommodation;



public class SortAccommodationCommand extends SortCommand {
    public static final String COMMAND_WORD = "sort accommodation";

    public static final String MESSAGE_USAGE =
            "sort accommodation: Sorts the list of accommodation in a travel plan by the keyword input by the user.\n"
                    + "Parameters: KEYWORD (COST/DATE)\n";

    public static final String MESSAGE_SORT_ACCOMMODATION_SUCCESS = "Sorted list of accommodation by: %1$s";

    public static final String MESSAGE_INVALID_KEYWORD = "INVALID KEYWORD! "
            + "Accommodation list can only sort by cost or date.";
    private final String keyword;

    /**
     * Constructor for SortAccommodationCommand.
     * @param keyword identifier to sort accommodation list.
     */
    public SortAccommodationCommand(String keyword) {
        super(keyword);
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isTravelPlan = model.isDirectoryTypeTravelPlan();
        if (!isTravelPlan) {
            throw new CommandException("Wishlist do not store accommodation!");
        }

        switch (keyword) {

        case SortCommand.KEYWORD_COST:
            Comparator<Accommodation> costComparator = Comparator.comparingInt(Accommodation::getCostAsInt);
            model.sortAccommodationList(costComparator);
            return new CommandResult(String.format(MESSAGE_SORT_ACCOMMODATION_SUCCESS, SortCommand.KEYWORD_COST));

        case SortCommand.KEYWORD_DATE:
            Comparator<Accommodation> dateComparator = Comparator.comparing(d -> d.getStartDate().getValue());
            model.sortAccommodationList(dateComparator);
            return new CommandResult(String.format(MESSAGE_SORT_ACCOMMODATION_SUCCESS, SortCommand.KEYWORD_DATETIME));

        case SortCommand.KEYWORD_NAME:
            Comparator<Accommodation> nameComparator = Comparator.comparing(d -> d.getName().toString());
            model.sortAccommodationList(nameComparator);
            return new CommandResult(String.format(MESSAGE_SORT_ACCOMMODATION_SUCCESS, SortCommand.KEYWORD_NAME));

        default:
            throw new CommandException(MESSAGE_INVALID_KEYWORD);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortAccommodationCommand // instanceof handles nulls
                && keyword.equals(((SortAccommodationCommand) other).keyword)); // state check
    }
}
