package seedu.address.logic.command.sort;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;


public class SortFriendCommand extends SortCommand {
    public static final String COMMAND_WORD = "sort friend";

    public static final String MESSAGE_USAGE =
            "sort friend: Sorts the list of friend in a travel plan by the keyword input by the user.\n"
                    + "Parameters: KEYWORD (NAME)\n";

    public static final String MESSAGE_SORT_FRIEND_SUCCESS = "Sorted list of friends by: %1$s";

    public static final String MESSAGE_INVALID_KEYWORD = "INVALID KEYWORD! "
            + "Friend list can only sort by name.";

    private final String keyword;

    /**
     * Constructor for SortFriendCommand.
     * @param keyword identifier to sort friend list.
     */
    public SortFriendCommand(String keyword) {
        super(keyword);
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isTravelPlan = model.isDirectoryTypeTravelPlan();
        if (!isTravelPlan) {
            throw new CommandException("Wishlist do not store friends!");
        }

        switch (keyword) {

        case SortCommand.KEYWORD_NAME:
            Comparator<Friend> nameComparator = Comparator.comparing(Friend::getNameAsString);
            model.sortFriendList(nameComparator);
            return new CommandResult(String.format(MESSAGE_SORT_FRIEND_SUCCESS, SortCommand.KEYWORD_NAME));

        default:
            throw new CommandException(MESSAGE_INVALID_KEYWORD);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortFriendCommand // instanceof handles nulls
                && keyword.equals(((SortFriendCommand) other).keyword)); // state check
    }
}
