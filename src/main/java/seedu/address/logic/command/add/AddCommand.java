package seedu.address.logic.command.add;

import seedu.address.logic.command.Command;

public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Adds a travel plan to the travel planner using the format:\n"
            + "add -travelplan n/NAME [sd/START_DATE ed/END_DATE]\n"
            + "Adds an activity to the current travel plan using the format:\n"
            + "add -activity n/NAME [i/LEVEL_OF_IMPORTANCE] [l/LOCATION] [c/COST] [d/DATE_AND_TIME] [t/tags]\n"
            + "Adds an accommodation to the current travel plan using the format:\n"
            + "add -accommodation n/NAME [l/LOCATION] [c/COST] [n/NIGHTS]\n"
            + "Adds a friend to the current travel plan using the format:\n"
            + "add -friend n/NAME [m/MOBILE_NUMBER] [p/PASSPORT_NUMBER]\n";
}
