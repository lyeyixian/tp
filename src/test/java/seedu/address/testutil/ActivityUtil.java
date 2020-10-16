package seedu.address.testutil;

import static seedu.address.logic.wanderlustlogic.wanderlustparser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.wanderlustlogic.wanderlustparser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.wanderlustlogic.wanderlustparser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.wanderlustlogic.wanderlustparser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.wanderlustlogic.wanderlustparser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.wanderlustlogic.wanderlustcommands.add.AddCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.edit.EditDescriptor;
import seedu.address.model.activity.Activity;

public class ActivityUtil {
    /**
     * Returns an add command string for adding the {@code activity}.
     */
    public static String getAddCommand(Activity activity) {
        return AddCommand.COMMAND_WORD + " -activity " + getActivityDetails(activity);
    }

    /**
     * Returns the part of command string for the given {@code activity}'s details.
     */
    public static String getActivityDetails(Activity activity) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + activity.getName().name + " ");
        sb.append(PREFIX_COST + activity.getCost().value + " ");
        sb.append(PREFIX_LOCATION + activity.getLocation().value + " ");
        sb.append(PREFIX_DATETIME + activity.getActivityDateTime().dateTime + " ");
        sb.append(PREFIX_IMPORTANCE + activity.getLevelOfImportance().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditactivityDescriptor}'s details.
     */
    public static String getEditActivityDescriptorDetails(EditDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.name).append(" "));
        descriptor.getCost().ifPresent(cost -> sb.append(PREFIX_COST).append(cost.value).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION).append(location.value).append(" "));
        descriptor.getActivityDateTime().ifPresent(date -> sb.append(PREFIX_DATETIME).append(date.dateTime)
                .append(" "));
        descriptor.getLevelOfImportance().ifPresent(importance -> sb.append(PREFIX_DATETIME).append(importance.value)
                .append(" "));
        return sb.toString();
    }

    /**
     * Returns edited activity for editActivity Command.
     * @param descriptor EditDescriptor
     * @return string for edit command.
     */
    public static String getNewEditActivityDescriptorDetails(EditDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append("Change Name").append(" "));
        return sb.toString();
    }
}
