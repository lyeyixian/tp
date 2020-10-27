package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.command.Command;
import seedu.address.logic.command.sort.SortAccommodationCommand;
import seedu.address.logic.command.sort.SortActivityCommand;
import seedu.address.logic.command.sort.SortCommand;
import seedu.address.logic.command.sort.SortFriendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.activity.Activity;
import seedu.address.model.friend.Friend;

public class SortCommandParser implements Parser<Command> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        try {
            String[] keywords = args.split(" ", 3);
            String travelPlanObjectType = keywords[1].substring(1);
            String sortKeyword = keywords[2];

            switch (travelPlanObjectType) {
            case Activity.TPO_WORD:
                return new SortActivityCommand(sortKeyword);

            case Accommodation.TPO_WORD:
                return new SortAccommodationCommand(sortKeyword);

            case Friend.TPO_WORD:
                return new SortFriendCommand(sortKeyword);

            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

    }
}
