package controller;

import network.APIManager;

import java.util.Date;

/**
 * Created By Tony on 19/02/2018
 */
public class GetAppealsReportController extends ExportReportsController{

    @Override
    protected void loadWithDates(Date from, Date to) {
        APIManager.getInstance().exportAppealByDate(from, to, (print, e) -> {
            displayPrint(print);
        });
    }
}
