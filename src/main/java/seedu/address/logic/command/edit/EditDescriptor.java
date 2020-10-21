package seedu.address.logic.command.edit;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOBILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.activity.Importance;
import seedu.address.model.activity.WanderlustDateTime;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;
import seedu.address.model.friend.Mobile;
import seedu.address.model.friend.Passport;

/**
 * Class to build description
 */
public class EditDescriptor {

    //identity
    private Name name;

    //all possible fields
    private Location location;
    private Cost cost;
    private WanderlustDate startDate;
    private WanderlustDate endDate;
    private Passport passport;
    private Mobile mobile;
    private Importance levelOfImportance;
    private WanderlustDateTime activityDateTime;

    public EditDescriptor() {

    }

    /**
     * Copy constructor
     *
     * @param toCopy contains information about all possible fields
     */
    public EditDescriptor(EditDescriptor toCopy) {
        toCopy.getName().ifPresent(this::setName);
        toCopy.getLocation().ifPresent(this::setLocation);
        toCopy.getCost().ifPresent(this::setCost);
        toCopy.getStartDate().ifPresent(this::setStartDate);
        toCopy.getEndDate().ifPresent(this::setEndDate);
        toCopy.getPassport().ifPresent(this::setPassport);
        toCopy.getMobile().ifPresent(this::setMobile);
        toCopy.getLevelOfImportance().ifPresent(this::setLevelOfImportance);
        toCopy.getActivityDateTime().ifPresent(this::setActivityDateTime);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, location, cost, levelOfImportance, activityDateTime, mobile, passport,
                startDate, endDate);
    }

    /**
     * Provides editdescriptor for edit commands
     *
     * @param source of tokenized fields
     * @return editdescriptor
     */
    public static EditDescriptor buildFromSource(ArgumentMultimap source) throws ParseException {

        EditDescriptor editItemnDescriptor = new EditDescriptor();

        if (source.getValue(PREFIX_NAME).isPresent()) {
            editItemnDescriptor.setName(ParserUtil.parseName(source.getValue(PREFIX_NAME).get()));
        }

        if (source.getValue(PREFIX_LOCATION).isPresent()) {
            editItemnDescriptor.setLocation(ParserUtil.parseLocation(source.getValue(PREFIX_LOCATION).get()));
        }
        if (source.getValue(PREFIX_COST).isPresent()) {
            editItemnDescriptor.setCost(ParserUtil.parseCost(source.getValue(PREFIX_COST).get()));
        }
        if (source.getValue(PREFIX_START).isPresent()) {
            editItemnDescriptor.setStartDate(ParserUtil.parseDate(source.getValue(PREFIX_START).get()));
        }
        if (source.getValue(PREFIX_END).isPresent()) {
            editItemnDescriptor.setEndDate(ParserUtil.parseDate(source.getValue(PREFIX_END).get()));
        }
        if (source.getValue(PREFIX_PASSPORT).isPresent()) {
            editItemnDescriptor.setPassport(ParserUtil.parsePassport(source.getValue(PREFIX_PASSPORT).get()));
        }
        if (source.getValue(PREFIX_MOBILE).isPresent()) {
            editItemnDescriptor.setMobile(ParserUtil.parseMobile(source.getValue(PREFIX_MOBILE).get()));
        }
        if (source.getValue(PREFIX_IMPORTANCE).isPresent()) {
            editItemnDescriptor.setLevelOfImportance(ParserUtil.parseImportance(source
                    .getValue(PREFIX_IMPORTANCE).get()));
        }
        if (source.getValue(PREFIX_DATETIME).isPresent()) {
            editItemnDescriptor.setActivityDateTime(ParserUtil.parseActivityDateTime(source.getValue(PREFIX_DATETIME)
                    .get()));
        }

        if (!editItemnDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return editItemnDescriptor;

    }


    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Optional<Location> getLocation() {
        return Optional.ofNullable(location);
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Optional<Cost> getCost() {
        return Optional.ofNullable(cost);
    }

    public void setLevelOfImportance(Importance importance) {
        this.levelOfImportance = importance;
    }

    public Optional<Importance> getLevelOfImportance() {
        return Optional.ofNullable(levelOfImportance);
    }

    public void setActivityDateTime(WanderlustDateTime activityDateTime) {
        this.activityDateTime = activityDateTime;
    }

    public Optional<WanderlustDateTime> getActivityDateTime() {
        return Optional.ofNullable(activityDateTime);
    }

    public void setStartDate(WanderlustDate startDate) {
        this.startDate = startDate;
    }

    public Optional<WanderlustDate> getStartDate() {
        return Optional.ofNullable(startDate);
    }

    public void setEndDate(WanderlustDate endDate) {
        this.endDate = endDate;
    }

    public Optional<WanderlustDate> getEndDate() {
        return Optional.ofNullable(endDate);
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Optional<Passport> getPassport() {
        return Optional.ofNullable(passport);
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public Optional<Mobile> getMobile() {
        return Optional.ofNullable(mobile);
    }

    /**
     * Descriptors are considered as the same if both descriptor have the same name
     *
     * @param other name to check
     */
    public boolean isSameDescriptor(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EditDescriptor)) {
            return false;
        }
        EditDescriptor toCompare = (EditDescriptor) other;
        return this.name.equals(toCompare.getName().get());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDescriptor)) {
            return false;
        }

        // state check
        EditDescriptor e = (EditDescriptor) other;

        return getName().equals(e.getName())
                && getActivityDateTime().equals(e.getActivityDateTime())
                && getLocation().equals(e.getLocation())
                && getCost().equals(e.getCost())
                && getStartDate().equals(e.getStartDate())
                && getEndDate().equals(e.getEndDate())
                && getPassport().equals(e.getPassport())
                && getMobile().equals(e.getMobile())
                && getLevelOfImportance().equals(e.getLevelOfImportance())
                && getActivityDateTime().equals(e.getActivityDateTime());

    }
}
