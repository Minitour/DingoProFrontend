package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Partnership;
import ui.UIView;

/**
 * Created By Tony on 17/02/2018
 */
public class TeamCellView extends UIView {

    @FXML
    private Label teamId;

    @FXML
    private Label reports;

    public TeamCellView(Partnership item) {
        super("/resources/xml/list_item_5.fxml");
        teamId.setText("Team #"+item.getPtshipNum());
        reports.setText("Assigned reports: "+item.getOfficers().size());
    }
}
