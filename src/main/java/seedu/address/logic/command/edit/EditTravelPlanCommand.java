package seedu.address.logic.command.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;
import seedu.address.model.travelplan.AccommodationList;
import seedu.address.model.travelplan.ActivityList;
import seedu.address.model.travelplan.FriendList;
import seedu.address.model.travelplan.TravelPlan;


/**
 * Edits an existing TravelPlan in the address book.
 * A travelplan contains the field name, start date and end date
 */
public class EditTravelPlanCommand extends EditCommand {
    public static final String COMMAND_WORD = "travelplan";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the travel plan identified by the index number used in the displayed travel plan list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_START + "START_DATE] "
            + "[" + PREFIX_END + "END_DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Trip to Japan "
            + PREFIX_START + "2020-01-20 "
            + PREFIX_END + " 2020-01-30";

    public static final String MESSAGE_EDIT_TRAVELPLAN_SUCCESS = "Edited Travel Plan: %1$s";
    public static final String MESSAGE_DUPLICATE_TRAVELPLAN = "This travelplan already exists in Wanderlust.";

    private final Index targetIndex;
    private final EditDescriptor editTravelPlanDescriptor;

    /**
     * Constructor for edit travel plan
     */
    public EditTravelPlanCommand(Index targetIndex, EditDescriptor editTravelPlanDescriptor) {
        super(targetIndex);
        this.targetIndex = targetIndex;
        this.editTravelPlanDescriptor = editTravelPlanDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TravelPlan> lastShownList = model.getFilteredTravelPlanList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRAVELPLAN_DISPLAYED_INDEX);
        }

        TravelPlan travelPlanToEdit = lastShownList.get(targetIndex.getZeroBased());
        TravelPlan editedTravelPlan = createEditedTravelPlan(travelPlanToEdit, editTravelPlanDescriptor);

        if (!travelPlanToEdit.isSameTravelPlan(editedTravelPlan) && model.hasTravelPlan(editedTravelPlan)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRAVELPLAN);
        }

        model.setTravelPlan(travelPlanToEdit, editedTravelPlan);
        return new CommandResult(String.format(MESSAGE_EDIT_TRAVELPLAN_SUCCESS, editedTravelPlan));
    }

    /**
     * Creates and returns a {@code TravelPlan} with the details of {@code travelPlanToEdit}
     *
     * @param travelPlanToEdit         contains the old fields
     * @param editTravelPlanDescriptor contains updated fields
     * @return TravelPlan to be updated in the travelplan list
     */
    private static TravelPlan createEditedTravelPlan(TravelPlan travelPlanToEdit,
                                                     EditDescriptor editTravelPlanDescriptor) {
        assert travelPlanToEdit != null;

        Name updatedName = editTravelPlanDescriptor.getName().orElse(travelPlanToEdit.getName());
        WanderlustDate updatedStartDate = editTravelPlanDescriptor.getStartDate()
                .orElse(travelPlanToEdit.getStartDate());
        WanderlustDate updatedEndDate = editTravelPlanDescriptor.getEndDate().orElse(travelPlanToEdit.getEndDate());

        //obtain data list from original travelplan
        ActivityList activities = travelPlanToEdit.getActivities();
        AccommodationList accommodations = travelPlanToEdit.getAccommodations();
        FriendList friends = travelPlanToEdit.getFriends();

        return new TravelPlan(updatedName, updatedStartDate, updatedEndDate, activities, accommodations, friends);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommand // instanceof handles nulls
                && targetIndex.equals(((EditTravelPlanCommand) other).targetIndex)) // state check
                && (editTravelPlanDescriptor.equals(((EditTravelPlanCommand) other).editTravelPlanDescriptor)
                || (editTravelPlanDescriptor.isSameDescriptor(((EditTravelPlanCommand) other)
                .editTravelPlanDescriptor)));
    }

}
