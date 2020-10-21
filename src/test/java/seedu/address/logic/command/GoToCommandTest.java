package seedu.address.logic.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.GoToCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.typicals.TypicalIndexes;

public class GoToCommandTest {
    @Test
    public void execute_goto_success() {
        try {
            Index index = TypicalIndexes.INDEX_FIRST_PERSON;
            GoToCommand expectedCommand = new GoToCommand(index, true);
            Assertions.assertTrue(expectedCommand.equals(new GoToCommandParser().parse(" -activity 1")));
        } catch (ParseException pe) {
            System.out.println("Invalid index!");
        }
    }

    @Test
    public void execute_goto_failure() {
        try {
            Index index = TypicalIndexes.INDEX_FIRST_PERSON;
            GoToCommand expectedCommand = new GoToCommand(index, false);
            Assertions.assertFalse(expectedCommand.equals(new GoToCommandParser()
                    .parse(" -travelplan 1")));
        } catch (ParseException pe) {
            System.out.println("Invalid index!");
        }
    }
}
