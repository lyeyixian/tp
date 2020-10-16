package seedu.address.testutil.typicals;

import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.VALID_COST_HOME;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.VALID_COST_INN;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.VALID_END_DATE_HOME;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.VALID_END_DATE_INN;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.VALID_LOCATION_HOME;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.VALID_LOCATION_INN;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.VALID_NAME_HOME;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.VALID_NAME_INN;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.VALID_START_DATE_HOME;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.VALID_START_DATE_INN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.travelplan.AccommodationList;
import seedu.address.testutil.builders.AccommodationBuilder;

public class TypicalAccommodations {

    public static final Accommodation ALICEHOTEL = new AccommodationBuilder().withName("Alice Hotel")
            .withStartDate("2020-01-01").withEndDate("2020-02-02")
            .withCost("100").withLocation("109 Alice Avenue").build();
    public static final Accommodation BOBHOTEL = new AccommodationBuilder().withName("Hostel by Bob")
            .withStartDate("2020-02-02").withEndDate("2020-03-03")
            .withCost("200").withLocation("9 Balestier Road").build();
    public static final Accommodation CARLHOTEL = new AccommodationBuilder().withName("Carl's Farmhouse")
            .withStartDate("2020-03-03").withEndDate("2020-04-04")
            .withCost("300").withLocation("999 Calihan Street").build();
    public static final Accommodation DANHOTEL = new AccommodationBuilder().withName("Danny's Lodge")
            .withStartDate("2020-04-04").withEndDate("2020-05-05")
            .withCost("400").withLocation("1 Den Street").build();
    public static final Accommodation ELLEHOTEL = new AccommodationBuilder().withName("Elle's")
            .withStartDate("2020-05-05").withEndDate("2020-06-06")
            .withCost("500").withLocation("333 Elmo's World").build();
    public static final Accommodation FIONAHOTEL = new AccommodationBuilder().withName("Fiona Hotel")
            .withStartDate("2020-06-06").withEndDate("2020-07-07")
            .withCost("600").withLocation("Fall Home").build();
    public static final Accommodation GEORGEHOTEL = new AccommodationBuilder().withName("George and Georgina")
            .withStartDate("2020-07-07").withEndDate("2020-08-08")
            .withCost("700").withLocation("7 GeorgeTown").build();

    // Manually added - Accommodations' details found in {@code CommandTestUtil}
    public static final Accommodation HOME = new AccommodationBuilder().withName(VALID_NAME_HOME)
            .withStartDate(VALID_START_DATE_HOME).withEndDate(VALID_END_DATE_HOME)
            .withCost(VALID_COST_HOME).withLocation(VALID_LOCATION_HOME).build();

    public static final Accommodation INN = new AccommodationBuilder().withName(VALID_NAME_INN)
            .withStartDate(VALID_START_DATE_INN).withEndDate(VALID_END_DATE_INN)
            .withCost(VALID_COST_INN).withLocation(VALID_LOCATION_INN).build();

    private TypicalAccommodations() {} // prevents instantiation

    /**
     * Returns an {@code AccommodationList} with a set of typical accommodations (either set 1 or set 2).
     */
    public static AccommodationList getTypicalAccommodationList(int set) {
        if (set != 1 && set != 2) {
            throw new IllegalArgumentException("getTypicalAccommodationList only takes in set 1 or 2 as argument.");
        }
        List<Accommodation> accommodations = set == 1 ? getTypicalAccommodations1() : getTypicalAccommodations2();
        AccommodationList al = new AccommodationList();
        for (Accommodation accommodation : accommodations) {
            al.addAccommodation(accommodation);
        }
        return al;
    }

    public static List<Accommodation> getTypicalAccommodations1() {
        return new ArrayList<>(Arrays.asList(ALICEHOTEL, BOBHOTEL, CARLHOTEL, DANHOTEL));
    }

    public static List<Accommodation> getTypicalAccommodations2() {
        return new ArrayList<>(Arrays.asList(ELLEHOTEL, FIONAHOTEL, GEORGEHOTEL));
    }
}
