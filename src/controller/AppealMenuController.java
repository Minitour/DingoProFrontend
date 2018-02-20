package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Appeal;
import network.APIManager;
import view.cells.AppealCell;
import view.AppealView;

import java.util.ResourceBundle;

/**
 * Created by Antonio Zaitoun on 14/01/2018.
 */
public class AppealMenuController extends SplitViewController{

    @FXML
    private ListView<Appeal> listView;

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);
        listView.setCellFactory(param -> new AppealCell());
    }

    public void refresh(){
        APIManager.getInstance().getAppeals((response, appeals, exception) -> {
            listView.getItems().clear();
            listView.getItems().addAll(appeals);
        });
    }

    @Override
    protected void onListItemChanged(int value) {
        Appeal appeal = listView.getItems().get(value);

        AppealView view = new AppealView(appeal);

        showView(view);
    }

    @Override
    public String title() {
        return "Appeals";
    }

}
