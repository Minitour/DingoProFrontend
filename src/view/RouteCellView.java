package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Route;
import ui.UIView;

/**
 * Created By Tony on 17/02/2018
 */
public class RouteCellView extends UIView {
    @FXML
    private Label menu;

    public RouteCellView(Route item) {
        super("/resources/xml/list_item.fxml");
        menu.setText(item.getSerialNum().toString());
    }
}
