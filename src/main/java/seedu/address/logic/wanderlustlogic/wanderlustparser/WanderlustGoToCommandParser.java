package seedu.address.logic.wanderlustlogic.wanderlustparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.GoToCommand;
import seedu.address.logic.wanderlustlogic.wanderlustparser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class WanderlustGoToCommandParser implements WanderlustParserInterface<GoToCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GoToCommand
     * and returns a GoToCommand object for execution.
     * @throws seedu.address.logic.parser.exceptions.ParseException if the user input does not conform
     * the expected format.
     */
    public GoToCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new GoToCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE), pe);
        }
    }


}
