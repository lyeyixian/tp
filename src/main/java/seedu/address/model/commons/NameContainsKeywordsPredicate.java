package seedu.address.model.commons;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.travelplan.TravelPlan;


/**
 * Tests that a {@code TravelPlanObject}'s or {@code TravelPlan}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<TravelPlan> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(TravelPlan tp) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tp.getName().name, keyword));
    }

    /**
     * Overloaded method to account for travel plan object.
     * @param travelPlanObject object to be tested.
     * @return true or false.
     */
    public boolean test(TravelPlanObject travelPlanObject) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(travelPlanObject.getName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
