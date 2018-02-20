package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Report;
import network.APIManager;
import view.cells.ReportCell;
import view.cells.TicketCreateView;

import java.util.ResourceBundle;

/**
 * Created by Antonio Zaitoun on 14/01/2018.
 */
public class ReportMenuController extends SplitViewController{

    @FXML
    private ListView<Report> listView;

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);
        listView.setCellFactory(param -> new ReportCell());
    }

    public void refresh(){
        APIManager.getInstance().getAllReports((response, reports, exception) -> {
            listView.getItems().clear();
            listView.getItems().addAll(reports);
        });
    }

    @Override
    protected void onListItemChanged(int value) {
        Report report = listView.getItems().get(value);
        TicketCreateView view = new TicketCreateView(report);

        showView(view);
    }

    @Override
    public String title() {
        return "Tickets";
    }

}
