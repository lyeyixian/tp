package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.logic.command.Command;
import seedu.address.logic.command.FindCommand;
import seedu.address.logic.command.delete.DeleteActivityCommand;
import seedu.address.logic.command.edit.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parseCommand(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the DeleteCommand parsing of {@code userInput} by {@code parser} is successful
     * and the command created equals to {@code expectedCommand}.
     */
    public static void assertWanderLustDeleteParseSuccess(DeleteCommandParser parser, String userInput,
                                                          DeleteActivityCommand expectedCommand) {
        try {
            seedu.address.logic.command.Command command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (seedu.address.logic.parser.exceptions.ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the FindCommand parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertFindParseSuccess(FindCommandParser parser, String userInput,
                                              FindCommand expectedCommand) {
        try {
            FindCommand command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     @@ -35,4 +51,18 @@ public static void assertParseFailure(Parser parser, String userInput, String ex
     assertEquals(expectedMessage, pe.getMessage());
     }
     }

     /**
      * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
      * equals to {@code expectedMessage}.
     */
    public static void assertWanderLustParseFailure(DeleteCommandParser parser, String userInput,
                                                    String expectedMessage) {
        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (seedu.address.logic.parser.exceptions.ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }

    /**
     * Asserts that the parsing an EditCommand of {@code userInput} by {@code parser} is
     * successful and the command created equals to {@code expectedCommand}.
     */
    public static void assertWanderLustParseEditCommandSuccess(EditCommandParser parser, String userInput,
                                                               EditCommand expectedCommand) {
        try {
            EditCommand command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (seedu.address.logic.parser.exceptions.ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     @@ -35,4 +51,18 @@ public static void assertParseFailure(Parser parser, String userInput, String ex
     assertEquals(expectedMessage, pe.getMessage());
     }
     }

     /**
      * Asserts that the parsing of an EditCommand {@code userInput} by {@code parser} is unsuccessful
      * and the error message equals to {@code expectedMessage}.
     */
    public static void assertWanderLustParseEditCommandFailure(EditCommandParser parser, String userInput,
                                                               String expectedMessage) {
        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (seedu.address.logic.parser.exceptions.ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }


}
