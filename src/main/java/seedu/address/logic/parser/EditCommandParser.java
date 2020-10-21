package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOBILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.edit.EditAccommodationCommand;
import seedu.address.logic.command.edit.EditActivityCommand;
import seedu.address.logic.command.edit.EditCommand;
import seedu.address.logic.command.edit.EditDescriptor;
import seedu.address.logic.command.edit.EditFriendCommand;
import seedu.address.logic.command.edit.EditTravelPlanCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements ParserInterface<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String[] keywords = args.split(" ");
        String editType = keywords[1].substring(1);
        try {
            switch (editType) {

            case EditActivityCommand.COMMAND_WORD:
                return parseActivity(args);

            case EditAccommodationCommand.COMMAND_WORD:
                return parseAccommodation(args);

            case EditFriendCommand.COMMAND_WORD:
                return parseFriend(args);

            case EditTravelPlanCommand.COMMAND_WORD:
                return parseTravelPlan(args);

            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));

            }

        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
    }

    EditActivityCommand parseActivity(String args) throws ParseException {
        try {
            String[] keywords = args.split(" ");
            Index index = ParserUtil.parseIndex(keywords[2]);

            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_IMPORTANCE, PREFIX_COST,
                    PREFIX_LOCATION, PREFIX_DATETIME);
            EditDescriptor editDescriptor = EditDescriptor.buildFromSource(argMultimap);

            return new EditActivityCommand(index, editDescriptor);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditActivityCommand.MESSAGE_USAGE));
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditActivityCommand.SPECIFY_INDEX));
        }
    }

    EditAccommodationCommand parseAccommodation(String args) throws ParseException {
        try {
            String[] keywords = args.split(" ");
            Index index = ParserUtil.parseIndex(keywords[2]);
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COST, PREFIX_LOCATION,
                    PREFIX_START, PREFIX_END);
            EditDescriptor editDescriptor = EditDescriptor.buildFromSource(argMultimap);

            return new EditAccommodationCommand(index, editDescriptor);
        } catch (ParseException e) {
            throw new ParseException((String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAccommodationCommand.MESSAGE_USAGE)));
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAccommodationCommand.SPECIFY_INDEX));
        }
    }

    EditFriendCommand parseFriend(String args) throws ParseException {
        try {
            String[] keywords = args.split(" ");
            Index index = ParserUtil.parseIndex(keywords[2]);
            ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PASSPORT,
                    PREFIX_MOBILE);
            EditDescriptor editDescriptor = EditDescriptor.buildFromSource(argumentMultimap);

            return new EditFriendCommand(index, editDescriptor);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFriendCommand.MESSAGE_USAGE));
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFriendCommand.SPECIFY_INDEX));
        }
    }

    EditTravelPlanCommand parseTravelPlan(String args) throws ParseException {
        try {
            String[] keywords = args.split(" ");
            Index index = ParserUtil.parseIndex(keywords[2]);
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_START, PREFIX_END);
            EditDescriptor editDescriptor = EditDescriptor.buildFromSource(argMultimap);

            return new EditTravelPlanCommand(index, editDescriptor);
        } catch (ParseException e) {
            throw new ParseException((String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTravelPlanCommand.MESSAGE_USAGE)));
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTravelPlanCommand.SPECIFY_INDEX));
        }
    }


}
