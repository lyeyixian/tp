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
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.travelplanner.Model;
import seedu.address.model.travelplanner.ModelManager;
import seedu.address.model.travelplanner.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteAccommodationCommand}.
 */
public class DeleteAccommodationCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(0);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Accommodation accommodationToDelete = model.getFilteredAccommodationList()
                .get(INDEX_FIRST_TRAVELPLAN.getZeroBased());
        DeleteAccommodationCommand deleteAccommodationCommand = new DeleteAccommodationCommand(INDEX_FIRST_TRAVELPLAN);

        String expectedMessage = String.format(deleteAccommodationCommand.MESSAGE_DELETE_ACCOMMODATION_SUCCESS,
                accommodationToDelete);

        ModelManager expectedModel = new ModelManager(model.getTravelPlanner(), new UserPrefs());
        // expectedModel.deleteTravelPlanObject(accommodationToDelete);

        assertCommandSuccess(deleteAccommodationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAccommodationList().size() + 1);
        DeleteAccommodationCommand deleteAccommodationCommand = new DeleteAccommodationCommand(outOfBoundIndex);

        assertCommandFailure(deleteAccommodationCommand, model, Messages.MESSAGE_INVALID_ACCOMMODATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        // showTravelPlanAtIndex(model, INDEX_FIRST_TRAVELPLAN);

        Accommodation accommodationToDelete = model.getFilteredAccommodationList()
                .get(INDEX_FIRST_TRAVELPLAN.getZeroBased());
        DeleteAccommodationCommand deleteAccommodationCommand = new DeleteAccommodationCommand(INDEX_FIRST_TRAVELPLAN);

        String expectedMessage = String.format(DeleteAccommodationCommand.MESSAGE_DELETE_ACCOMMODATION_SUCCESS,
                accommodationToDelete);

        Model expectedModel = new ModelManager(model.getTravelPlanner(), new UserPrefs());
        // expectedModel.deleteTravelPlanObject(accommodationToDelete);
        showNoAccommodationList(expectedModel);

        assertCommandSuccess(deleteAccommodationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTravelPlanAtIndex(model, INDEX_FIRST_TRAVELPLAN);

        Index outOfBoundIndex = INDEX_TEN_TRAVELPLAN;

        DeleteAccommodationCommand deleteAccommodationCommand = new DeleteAccommodationCommand(outOfBoundIndex);

        assertCommandFailure(deleteAccommodationCommand, model, Messages.MESSAGE_INVALID_ACCOMMODATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteAccommodationCommand deleteFirstCommand = new DeleteAccommodationCommand(INDEX_FIRST_TRAVELPLAN);
        DeleteAccommodationCommand deleteSecondCommand = new DeleteAccommodationCommand(INDEX_SECOND_TRAVELPLAN);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAccommodationCommand deleteFirstCommandCopy = new DeleteAccommodationCommand(INDEX_FIRST_TRAVELPLAN);
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
    private void showNoAccommodationList(Model model) {
        model.updateFilteredAccommodationList(p -> false);

        assertTrue(model.getFilteredAccommodationList().isEmpty());
    }
}
