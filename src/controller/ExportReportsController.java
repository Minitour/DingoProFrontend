package controller;

import com.jfoenix.controls.JFXDatePicker;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import ui.UIViewController;

import javax.swing.*;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created By Tony on 16/02/2018
 */
public class ExportReportsController extends UIViewController {

    @FXML
    protected JFXDatePicker fromDate;

    @FXML
    protected JFXDatePicker toDate;

    @FXML
    private AnchorPane jasper_container;

    public ExportReportsController() {
        super("/resources/xml/controller_export_reports.fxml");
    }

    private Date dateFrom;

    private Date dateTo;

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);

        EventHandler<ActionEvent> eventEventHandler = (event) -> {
            try {
                Date dateFrom = java.sql.Date.from(fromDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date dateTo = java.sql.Date.from(toDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                if(dateFrom.getTime() < dateTo.getTime()){
                    //show reports
                    loadWithDates(dateFrom,dateTo);
                }
            }catch (Exception ignored){ }
        };

        fromDate.setOnAction(eventEventHandler);
        toDate.setOnAction(eventEventHandler);
    }

    protected void loadWithDates(Date from,Date to){
    }

    protected void displayPrint(JasperPrint print){
        SwingNode swingNode = new SwingNode();
        JRViewer viewer = new JRViewer(print);
        viewer.setZoomRatio(0.75f);
        createSwingContent(swingNode,viewer);

        AnchorPane.setBottomAnchor(swingNode,0.0);
        AnchorPane.setLeftAnchor(swingNode,0.0);
        AnchorPane.setRightAnchor(swingNode,0.0);
        AnchorPane.setTopAnchor(swingNode,0.0);
        jasper_container.getChildren().clear();
        jasper_container.getChildren().add(swingNode);
    }
    private static void createSwingContent(final SwingNode swingNode,JRViewer viewer) {
        SwingUtilities.invokeLater(() -> swingNode.setContent(viewer));
    }
}
