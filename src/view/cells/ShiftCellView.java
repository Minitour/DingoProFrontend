package view.cells;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Shift;
import ui.UIView;

/**
 * Created By Tony on 17/02/2018
 */
public class ShiftCellView extends UIView {
    @FXML
    private Label reportId;

    @FXML
    private Label description;

    @FXML
    private Label date;

    public ShiftCellView(Shift item) {
        super("/resources/xml/list_item_2.fxml");
        reportId.setText(item.getShiftCode().toString());
        description.setText(item.getType());
        date.setText(item.getShiftDate().toLocaleString());
    }
}
