package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.travelplan.TravelPlan;
import seedu.address.model.travelplanner.Directory;


/**
 * Panel containing basic information of a Travel Plan.
 */
public class TravelPlanPanel extends UiPart<Region> {
    private static final String TravelPlanFXML = "TravelPlanPanel.fxml";
    private static final String WishlistFXML = "WishlistPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TravelPlanPanel.class);

    private Directory directory;

    @FXML
    private Label name;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;

    /**
     * Creates a {@code TravelPlanObjectListPanel} with the given {@code ObservableList}.
     */
    public TravelPlanPanel(Directory directory) {
        super(directory instanceof TravelPlan ? TravelPlanFXML : WishlistFXML);
        this.directory = directory;
        update();
    }

    /**
     * Updates the JavaFX properties of {@code TravelPlanPanel} according to the directory.
     */
    public void update() {
        if (directory instanceof TravelPlan) {
            TravelPlan travelPlan = (TravelPlan) directory;
            name.setText(travelPlan.getName().toString());
            startDate.setText(travelPlan.getStartDate().toString());
            endDate.setText(travelPlan.getEndDate().toString());
        } else {
            name.setText("Wishlist");
        }
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
        update();
    }
}
