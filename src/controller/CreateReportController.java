package controller;

import com.jfoenix.controls.JFXSnackbar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.Report;
import network.APIManager;
import ui.UIViewController;
import view.TicketCreateView;

/**
 * Created By Tony on 20/02/2018
 */
public class CreateReportController extends UIViewController {

    @FXML
    private Button create;

    @FXML
    private Button reset;

    @FXML
    private Pane holder;

    private TicketCreateView ticketCreateView;

    public CreateReportController() {
        super("/resources/xml/controller_report.fxml");
        ticketCreateView = new TicketCreateView();
        holder.getChildren().add(ticketCreateView);

        create.setOnAction(event -> {
            Report report = ticketCreateView.getReport();
            APIManager.getInstance().createReport(report, (response, exception) -> {
                JFXSnackbar bar = new JFXSnackbar(this.view);
                if (response.isOK()) {
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("Report Created"));
                    ticketCreateView.reset();
                }
                else
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("Something went wrong: "+response.getMessage()));

            });
        });

        reset.setOnAction(event -> ticketCreateView.reset());
    }



}
