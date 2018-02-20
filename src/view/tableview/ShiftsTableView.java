package view.tableview;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.Shift;
import ui.UITableView;

import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created By Tony on 17/02/2018
 */
public class ShiftsTableView extends UITableView<Shift> {

    final static String[] columns = {"Shift ID","Shift Date","Type"};

    private Button button;

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        Pane toolbar = getToolBar();
        button = new JFXButton("Create Shift");
        button.getStyleClass().add("button-raised");
        button.setOnAction(event -> onCreateTeamClicked());

        toolbar.getChildren().add(button);

    }

    @Override
    public int numberOfColumns() {
        return columns.length;
    }

    public void onCreateTeamClicked(){

    }

    @Override
    public Collection<? extends Shift> dataSource() {
        return null;
    }

    @Override
    public String bundleIdForIndex(int index) {
        return columns[index];
    }

    @Override
    public TableColumnValue<Shift> cellValueForColumnAt(int index) {
        switch (index){
            case 0:
                return Shift::getShiftCode;
            case 1:
                return shift -> shift.getShiftDate().toLocaleString();
            case 2:
                return Shift::getType;
        }
        return null;
    }

}


