package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertFindParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.command.FindCommand;
import seedu.address.model.commons.NameContainsKeywordsPredicate;

public class WanderlustFindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<String> keywords = Arrays.asList("foo", "bar", "baz"); //find activity

        assertFindParseSuccess(parser, "find -activity foo bar baz",
                new FindCommand(new NameContainsKeywordsPredicate(keywords), 0));
    }

}
