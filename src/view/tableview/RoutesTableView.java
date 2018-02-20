package view.tableview;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.Route;
import ui.UITableView;

import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created By Tony on 17/02/2018
 */
public class RoutesTableView extends UITableView<Route> {

    final static String[] columns = {"Route ID","Creation Date","Landmarks"};

    private Button button;

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        Pane toolbar = getToolBar();
        button = new JFXButton("Create Route");
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
    public Collection<? extends Route> dataSource() {
        return null;
    }

    @Override
    public String bundleIdForIndex(int index) {
        return columns[index];
    }

    @Override
    public TableColumnValue<Route> cellValueForColumnAt(int index) {
        switch (index){
            case 0:
                return Route::getSerialNum;
            case 1:
                return Route -> Route.getCreationDate().toLocaleString();
            case 2:
                return Route -> Route.getLandmarks().size();
        }
        return null;
    }

}


