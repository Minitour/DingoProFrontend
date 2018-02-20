package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Shift;
import network.APIManager;
import ui.UITableView;
import ui.UIViewController;
import view.DynamicDialog;
import view.ShiftFormView;
import view.tableview.ShiftsTableView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By Tony on 17/02/2018
 */
public class ManageShiftsController extends UIViewController {
    @FXML
    private Pane tableHolder;

    private UITableView<Shift> tableView;
    private DynamicDialog dialogView;

    public ManageShiftsController() {
        super("/resources/xml/controller_tableview_supporter.fxml");
    }

    private void showTeamCreationDialog(){
        ShiftFormView shiftFormView = new ShiftFormView();
        dialogView = new DynamicDialog(shiftFormView);

        dialogView.getNegativeButton().setText("Cancel");
        dialogView.getPostiveButton().setText("Create");
        dialogView.setTitle("Create Shift");
        dialogView.setMessage("Are you sure you want to create a new shift?");
        dialogView.delegate(new DynamicDialog.DialogDelegate() {
            @Override
            public boolean onDone(DynamicDialog dialog) {
                if(!shiftFormView.isValid())
                    return false;

                Shift shift = shiftFormView.getShift();
                APIManager.getInstance().createShift(shift,(response, exception) -> {
                    refresh();
                });
                return true;
            }
        });


        dialogView.show(this.view);
    }


    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);



        tableView = new ShiftsTableView() {
            List<Shift> shifts = new ArrayList<>();

            @Override
            public Collection<? extends Shift> dataSource() {
                return shifts;
            }

            @Override
            public void onCreateTeamClicked() {
                showTeamCreationDialog();
            }

            @Override
            public void reloadData() {
                APIManager.getInstance().getShifts((response, teams, ex) -> {
                    if(ex==null){
                        if(response.isOK()){
                            try {
                                shifts = new ArrayList<>(teams);
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
