package controller;

import network.APIManager;

import java.util.Date;

/**
 * Created By Tony on 19/02/2018
 */
public class GetTicketsReportController extends ExportReportsController{
    @Override
    protected void loadWithDates(Date from, Date to) {
        APIManager.getInstance().exportReportByDate(from, to, (print, e) -> {
            displayPrint(print);
        });
    }
}