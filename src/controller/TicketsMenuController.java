package controller;

import model.Report;
import ui.UIView;
import view.cells.TicketCell;
import view.cells.TicketCreateView;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Antonio Zaitoun on 12/01/2018.
 */
public class TicketsMenuController extends SplitViewController{

    private List<Report> reportList;

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);

        //reportList = QueryHelper.getReports();

        listView.setCellFactory(param -> new TicketCell());

       //listView.getItems().addAll(reportList);

    }

    @Override
    public String title() {
        return "Tickets";
    }

    @Override
    protected void onListItemChanged(int value) {
        super.onListItemChanged(value);

        String reportId = reportList.get(value).getAlphaNum();

        Report report = null;//QueryHelper.getReport(reportId);

        UIView view = new TicketCreateView(report);

        showView(view);
    }
}
