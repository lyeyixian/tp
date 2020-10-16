package seedu.address.logic.wanderlustlogic.wanderlustcommands.delete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.showTravelPlanAtIndex;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_FIRST_TRAVELPLAN;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_SECOND_TRAVELPLAN;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_TEN_TRAVELPLAN;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.friend.Friend;
import seedu.address.model.travelplanner.Model;
import seedu.address.model.travelplanner.ModelManager;
import seedu.address.model.travelplanner.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteFriendCommand}.
 */
public class DeleteFriendCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(0);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Friend friendToDelete = model.getFilteredFriendList().get(INDEX_FIRST_TRAVELPLAN.getZeroBased());
        DeleteFriendCommand deleteFriendCommand = new DeleteFriendCommand(INDEX_FIRST_TRAVELPLAN);

        String expectedMessage = String.format(DeleteFriendCommand.MESSAGE_DELETE_FRIEND_SUCCESS,
                friendToDelete);

        ModelManager expectedModel = new ModelManager(model.getTravelPlanner(), new UserPrefs());
        //        expectedModel.deleteTravelPlanObject(friendToDelete);

        assertCommandSuccess(deleteFriendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFriendList().size() + 1);
        DeleteFriendCommand deleteFriendCommand = new DeleteFriendCommand(outOfBoundIndex);

        assertCommandFailure(deleteFriendCommand, model, Messages.MESSAGE_INVALID_FRIEND_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        //        showTravelPlanAtIndex(model, INDEX_FIRST_TRAVELPLAN);

        Friend friendToDelete = model.getFilteredFriendList().get(INDEX_FIRST_TRAVELPLAN.getZeroBased());
        DeleteFriendCommand deleteFriendCommand = new DeleteFriendCommand(INDEX_FIRST_TRAVELPLAN);

        String expectedMessage = String.format(DeleteFriendCommand.MESSAGE_DELETE_FRIEND_SUCCESS,
                friendToDelete);

        Model expectedModel = new ModelManager(model.getTravelPlanner(), new UserPrefs());
        //        expectedModel.deleteTravelPlanObject(friendToDelete);
        showNoFriendList(expectedModel);

        assertCommandSuccess(deleteFriendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTravelPlanAtIndex(model, INDEX_FIRST_TRAVELPLAN);

        Index outOfBoundIndex = INDEX_TEN_TRAVELPLAN;

        DeleteFriendCommand deleteFriendCommand = new DeleteFriendCommand(outOfBoundIndex);

        assertCommandFailure(deleteFriendCommand, model, Messages.MESSAGE_INVALID_FRIEND_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteFriendCommand deleteFirstCommand = new DeleteFriendCommand(INDEX_FIRST_TRAVELPLAN);
        DeleteFriendCommand deleteSecondCommand = new DeleteFriendCommand(INDEX_SECOND_TRAVELPLAN);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteFriendCommand deleteFirstCommandCopy = new DeleteFriendCommand(INDEX_FIRST_TRAVELPLAN);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different travelPlan -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoFriendList(Model model) {
        model.updateFilteredFriendList(p -> false);

        assertTrue(model.getFilteredFriendList().isEmpty());
    }
}
