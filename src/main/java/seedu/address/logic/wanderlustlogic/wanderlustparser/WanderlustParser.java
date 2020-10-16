package seedu.address.logic.wanderlustlogic.wanderlustparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.wanderlustlogic.wanderlustcommands.AddCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.ClearCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.Command;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.ExitCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.FindCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.GoToCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.HelpCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.ListCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.delete.DeleteCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.edit.EditCommand;
import seedu.address.logic.wanderlustlogic.wanderlustparser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class WanderlustParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new WanderlustAddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new WanderlustEditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new WanderlustDeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new WanderlustFindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case GoToCommand.COMMAND_WORD:
            return new WanderlustGoToCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
