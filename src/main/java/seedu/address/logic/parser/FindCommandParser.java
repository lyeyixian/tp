package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.command.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.activity.Activity;
import seedu.address.model.commons.NameContainsKeywordsPredicate;
import seedu.address.model.friend.Friend;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements ParserInterface<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        try {
            String[] keywords = args.split(" ", 3);
            String travelPlanObjectType = keywords[1].substring(1);

            String findWord = keywords[2];
            String[] nameKeywords = findWord.split("\\s+");


            switch (travelPlanObjectType) {
            case Friend.TPO_WORD:
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), 2);

            case Activity.TPO_WORD:
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), 0);

            case Accommodation.TPO_WORD:
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), 1);

            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MISSING_KEYWORDS));
        }

    }
}

