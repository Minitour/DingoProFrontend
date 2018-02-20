package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Report;
import network.APIManager;
import ui.UIView;
import view.DingoTicketView;
import view.cells.ReportCell;
import view.TicketCreateView;

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
        UIView view;
        if(report.getReport_type() == 1) {
            view = new TicketCreateView(report);
        }else{
            view = new DingoTicketView(report);
        }

        showView(view);
    }

    @Override
    public String title() {
        return "Tickets";
    }

}
