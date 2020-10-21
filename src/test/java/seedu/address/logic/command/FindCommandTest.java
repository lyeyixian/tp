package seedu.address.logic.command;

import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commons.NameContainsKeywordsPredicate;

public class FindCommandTest {

    private Model model;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(0);
    }

    @Test
    public void execute_find_success() {
        try {
            String[] arr = new String[]{"friend"};
            List<String> keywords = Arrays.asList(arr);

            FindCommand expectedCommand = new FindCommand(new NameContainsKeywordsPredicate(keywords), 2);
            Assertions.assertTrue(expectedCommand.equals(new FindCommandParser()
                    .parse("find -friend friend")));
        } catch (ParseException pe) {
            System.out.println("Invalid input!");
        }
    }

    @Test
    public void execute_findMissingDescription_failure() {
        List<String> keywords = Arrays.asList("foo", "bar", "baz"); //find activity
        FindCommand validCommand = new FindCommand(new NameContainsKeywordsPredicate(keywords), 0);
        try {
            Assertions.assertFalse(validCommand.equals(new FindCommandParser().parse("find -activity")));
        } catch (ParseException e) {
            System.out.println("Invalid input");
        }

    }
}
