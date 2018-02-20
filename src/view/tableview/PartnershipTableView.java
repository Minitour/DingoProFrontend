package view.tableview;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.Partnership;
import ui.UITableView;

import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created By Tony on 17/02/2018
 */
public class PartnershipTableView extends UITableView<Partnership> {

    final static String[] columns = {"Team ID","Creation Date","Members"};

    private Button button;

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        Pane toolbar = getToolBar();
        button = new JFXButton("Create Partnership");
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
    public Collection<? extends Partnership> dataSource() {
        return null;
    }

    @Override
    public String bundleIdForIndex(int index) {
        return columns[index];
    }

    @Override
    public UITableView.TableColumnValue<Partnership> cellValueForColumnAt(int index) {
        switch (index){
            case 0:
                return Partnership::getPtshipNum;
            case 1:
                return partnership -> partnership.getCreationDate().toLocaleString();
            case 2:
                return partnership -> partnership.getOfficers().size();
        }
        return null;
    }

}


