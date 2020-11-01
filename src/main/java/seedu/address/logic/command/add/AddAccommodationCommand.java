package seedu.address.logic.command.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.ParserUtil.ACCOMMODATION_INDEX;
import static seedu.address.model.accommodation.Accommodation.MESSAGE_DUPLICATE_ACCOMMODATION;

import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.accommodation.Accommodation;

public class AddAccommodationCommand extends AddCommand {
    public static final String COMMAND_WORD = "accommodation";

    public static final String MESSAGE_USAGE = AddCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Adds an accommodation to the current travel plan\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_LOCATION + "LOCATION "
            + PREFIX_COST + "COST "
            + PREFIX_START + "START_DATE "
            + PREFIX_END + "END_DATE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Intercontinental Singapore "
            + PREFIX_LOCATION + "80 Middle Road "
            + PREFIX_COST + "250 "
            + PREFIX_START + "2020-09-15 "
            + PREFIX_END + "30-09-2020 ";

    public static final String MESSAGE_SUCCESS = "New accommodation added: %1$s";

    private final Accommodation toAdd;

    /**
     * Creates an AddAccommodationCommand to add the specified {@code Accommodation}
     */
    public AddAccommodationCommand(Accommodation accommodation) {
        requireNonNull(accommodation);
        toAdd = accommodation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTravelPlanObject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACCOMMODATION);
        }

        model.addTravelPlanObject(toAdd);
        assert model.getAccommodationList().hasAccommodation(toAdd) : "Accommodation was not added";

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), ACCOMMODATION_INDEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAccommodationCommand // instanceof handles nulls
                && toAdd.equals(((AddAccommodationCommand) other).toAdd));
    }
}
