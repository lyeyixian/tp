package seedu.address.logic.command.add;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.travelplan.TravelPlan;

public class AddTravelPlanCommand extends AddCommand {
    public static final String COMMAND_WORD = "travelplan";

    public static final String MESSAGE_USAGE = AddCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Adds a travel plan to the travel planner\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_START + "START_DATE "
            + PREFIX_END + "END_DATE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "France "
            + PREFIX_START + "15-09-2020 "
            + PREFIX_END + "30-09-2020 ";

    public static final String MESSAGE_SUCCESS = "New travel plan added: %1$s";
    public static final String MESSAGE_DUPLICATE_TRAVEL_PLAN = "This travel plan already exists in the travel planner";

    private final TravelPlan toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddTravelPlanCommand(TravelPlan travelPlan) {
        requireNonNull(travelPlan);
        toAdd = travelPlan;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTravelPlan(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRAVEL_PLAN);
        }

        model.addTravelPlan(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTravelPlanCommand // instanceof handles nulls
                && toAdd.equals(((AddTravelPlanCommand) other).toAdd));
    }
}
