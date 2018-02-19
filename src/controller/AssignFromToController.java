package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ui.UIViewController;



/**
 * Created By Tony on 18/02/2018
 */
public class AssignFromToController<F,T> extends UIViewController {

    @FXML
    protected ListView<F> from;

    @FXML
    protected ListView<T> to;

    @FXML
    protected Button assign;

    @FXML
    private Label titleLabel;

    public AssignFromToController() {
        super("/resources/xml/controller_add_from_to.fxml");
        assign.setOnAction(event -> {
            try{
                onAssign(from.getSelectionModel().getSelectedItem(),
                        to.getSelectionModel().getSelectedItem());
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        });

        titleLabel.setText(title());
    }

    protected void onAssign(F from,T to){

    }
}
