package seedu.address.logic.command.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;
import seedu.address.model.travelplan.TravelPlan;

/**
 * Edits existing Accommodation in the address book. This command can only be used within the travel plan directory.
 * An accommodation contains the field name, location, cost, start date and end date
 */
public class EditAccommodationCommand extends EditCommand {
    public static final String COMMAND_WORD = "accommodation";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the accommodation identified by the index number used in the displayed accommodation list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_COST + "COST]"
            + "[" + PREFIX_START + "START_DATE] "
            + "[" + PREFIX_END + "END_DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Hard Rock Hotel "
            + PREFIX_LOCATION + "Sentosa "
            + PREFIX_COST + "500 "
            + PREFIX_START + "2020-07-10 "
            + PREFIX_END + "2020-07-20";


    public static final String MESSAGE_EDIT_ACCOMMODATION_SUCCESS = "Edited Accommodation: %1$s";
    public static final String MESSAGE_DUPLICATE_ACCOMMODATION = "This accommodation already exists in the "
            + "accommodation list";

    private final Index targetIndex;
    private final EditDescriptor editAccommodationDescriptor;

    /**
     * Constructor for edit accommodation
     */
    public EditAccommodationCommand(Index targetIndex, EditDescriptor editAccommodationDescriptor) {
        super(targetIndex);
        this.targetIndex = targetIndex;
        this.editAccommodationDescriptor = editAccommodationDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!(model.getDirectory() instanceof TravelPlan)) {
            throw new CommandException(MESSAGE_WRONG_DIRECTORY);
        }

        List<Accommodation> lastShownList = model.getFilteredAccommodationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACCOMMODATION_DISPLAYED_INDEX);
        }

        Accommodation accommodationToEdit = lastShownList.get(targetIndex.getZeroBased());
        Accommodation editedAccommodation = createEditedAccommodation(accommodationToEdit, editAccommodationDescriptor);

        if (!accommodationToEdit.isSameAccommodation(editedAccommodation)
                && lastShownList.contains(editedAccommodation)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACCOMMODATION);
        }

        model.setTravelPlanObject(accommodationToEdit, editedAccommodation);

        return new CommandResult(String.format(MESSAGE_EDIT_ACCOMMODATION_SUCCESS, editedAccommodation));
    }

    /**
     * Creates and returns a {@code Accommodation} with the details of {@code accommodationToEdit}
     *
     * @param accommodationToEdit         contains the old fields
     * @param editAccommodationDescriptor contains updated fields
     * @return Accommodation to be updated in the accommodation list
     */
    private static Accommodation createEditedAccommodation(Accommodation accommodationToEdit,
                                                           EditDescriptor editAccommodationDescriptor) {
        assert accommodationToEdit != null;

        Name updatedName = editAccommodationDescriptor.getName().orElse(accommodationToEdit.getName());
        Location updatedLocation = editAccommodationDescriptor.getLocation().orElse(accommodationToEdit.getLocation());
        Cost updatedCost = editAccommodationDescriptor.getCost().orElse(accommodationToEdit.getCost());
        WanderlustDate updatedStartDate = editAccommodationDescriptor.getStartDate()
                .orElse(accommodationToEdit.getStartDate());
        WanderlustDate updatedEndDate = editAccommodationDescriptor.getEndDate()
                .orElse(accommodationToEdit.getEndDate());


        return new Accommodation(updatedName, updatedStartDate, updatedEndDate, updatedCost, updatedLocation);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommand // instanceof handles nulls
                && targetIndex.equals(((EditAccommodationCommand) other).targetIndex)) // state check
                && (editAccommodationDescriptor.equals(((EditAccommodationCommand) other).editAccommodationDescriptor)
                || editAccommodationDescriptor.isSameDescriptor(((EditAccommodationCommand) other)
                .editAccommodationDescriptor));
    }
}
