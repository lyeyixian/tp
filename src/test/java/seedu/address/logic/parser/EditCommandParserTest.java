package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertWanderLustParseEditCommandFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertWanderLustParseEditCommandSuccess;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.command.edit.EditActivityCommand;
import seedu.address.logic.command.edit.EditDescriptor;
import seedu.address.logic.command.edit.EditFriendCommand;
import seedu.address.model.commons.Name;

public class EditCommandParserTest {

    private EditCommandParser parser = new EditCommandParser();

    //Parse EditFriend
    @Test
    public void parse_validArgs_returnsEditCommand() {

        EditDescriptor editDescriptor = new EditDescriptor();
        editDescriptor.setName(new Name("Bobby"));
        assertWanderLustParseEditCommandSuccess(parser, " -friend 1 n/Bobby",
                new EditFriendCommand(INDEX_FIRST, editDescriptor));
    }

    //Parse EditActivity
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertWanderLustParseEditCommandFailure(parser, " -activity a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditActivityCommand.MESSAGE_USAGE));
    }
}
