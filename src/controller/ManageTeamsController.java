package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Partnership;
import network.APIManager;
import ui.UITableView;
import ui.UIViewController;
import view.DialogView;
import view.tableview.PartnershipTableView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By Tony on 17/02/2018
 */
public class ManageTeamsController extends UIViewController {
    @FXML
    private Pane tableHolder;

    private UITableView<Partnership> tableView;
    private DialogView dialogView;

    public ManageTeamsController() {
        super("/resources/xml/controller_tableview_supporter.fxml");
    }

    private void showTeamCreationDialog(){
        dialogView = new DialogView();

        dialogView.getNegativeButton().setText("Cancel");
        dialogView.getPostiveButton().setText("Create");
        dialogView.setTitle("Create Team");
        dialogView.setMessage("Are you sure you want to create a new team?");
        dialogView.setNegativeEventHandler(event -> dialogView.close());
        dialogView.setPostiveEventHandler(event -> {
            APIManager.getInstance().createPartnership((response, exception) -> {
                refresh();
            });
            dialogView.close();
        });


        dialogView.show(this.view);
    }


    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);



        tableView = new PartnershipTableView() {
            List<Partnership> teamList = new ArrayList<>();

            @Override
            public Collection<? extends Partnership> dataSource() {
                return teamList;
            }

            @Override
            public void onCreateTeamClicked() {
                showTeamCreationDialog();
            }

            @Override
            public void reloadData() {
                APIManager.getInstance().getPartnerships((response, teams, ex) -> {
                    if(ex==null){
                        if(response.isOK()){
                            try {
                                teamList = new ArrayList<>(teams);
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
