package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Directory;
import seedu.address.model.travelplan.TravelPlan;


/**
 * Panel containing basic information of a Travel Plan.
 */
public class TravelPlanPanel extends UiPart<Region> {
    private static final String TravelPlanFXML = "TravelPlanPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TravelPlanPanel.class);

    private Directory directory;

    @FXML
    private Label name;
    @FXML
    private Label startDateToEndDate;

    /**
     * Creates a {@code TravelPlanObjectListPanel} with the given {@code ObservableList}.
     */
    public TravelPlanPanel(Directory directory) {
        super(TravelPlanFXML);
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
            startDateToEndDate.setText(travelPlan.getStartDate().toString() + " to "
                    + travelPlan.getEndDate().toString());
        } else {
            name.setText("Wishlist");
            startDateToEndDate.setText("");
        }
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
        update();
    }
}
