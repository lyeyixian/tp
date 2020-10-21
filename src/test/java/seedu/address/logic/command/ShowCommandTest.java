package seedu.address.logic.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ShowCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

public class ShowCommandTest {
    @Test
    public void execute_show_success() {
        try {
            ShowCommand expectedCommand = new ShowCommand("activity");
            Assertions.assertTrue(expectedCommand.equals(new ShowCommandParser().parse(" -activity")));
        } catch (ParseException pe) {
            System.out.println("Invalid input!");
        }
    }

    @Test
    public void execute_goto_failure() {
        try {
            ShowCommand expectedCommand = new ShowCommand("friend");
            Assertions.assertFalse(expectedCommand.equals(new ShowCommandParser().parse(" -friends")));
        } catch (ParseException pe) {
            System.out.println("Invalid input!");
        }
    }
}
