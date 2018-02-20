package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Route;
import network.APIManager;
import ui.UITableView;
import ui.UIViewController;
import view.DialogView;
import view.tableview.RoutesTableView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By Tony on 17/02/2018
 */
public class ManageRoutesController extends UIViewController {
    @FXML
    private Pane tableHolder;

    private UITableView<Route> tableView;
    private DialogView dialogView;

    public ManageRoutesController() {
        super("/resources/xml/controller_tableview_supporter.fxml");
    }

    private void showTeamCreationDialog(){
        dialogView = new DialogView();

        dialogView.getNegativeButton().setText("Cancel");
        dialogView.getPostiveButton().setText("Create");
        dialogView.setTitle("Create Route");
        dialogView.setMessage("Are you sure you want to create a new route?");
        dialogView.setNegativeEventHandler(event -> dialogView.close());
        dialogView.setPostiveEventHandler(event -> {
            APIManager.getInstance().createRoute((response, exception) -> {
                refresh();
            });
            dialogView.close();
        });


        dialogView.show(this.view);
    }


    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);



        tableView = new RoutesTableView() {
            List<Route> routes = new ArrayList<>();

            @Override
            public Collection<? extends Route> dataSource() {
                return routes;
            }

            @Override
            public void onCreateTeamClicked() {
                showTeamCreationDialog();
            }

            @Override
            public void reloadData() {
                APIManager.getInstance().getRoutes((response, r, ex) -> {
                    if(ex==null){
                        if(response.isOK()){
                            try {
                                routes = new ArrayList<>(r);
                                super.reloadData();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };

        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);

        tableHolder.getChildren().add(tableView);
        tableView.reloadData();
    }

    public void refresh(){
        tableView.reloadData();
    }


}
