package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import ui.UIView;
import ui.UIViewController;

import java.util.ResourceBundle;

/**
 * Created by Antonio Zaitoun on 12/01/2018.
 */
public class SplitViewController extends UIViewController {

    public SplitViewController(){
        super("/resources/xml/controller_splitview.fxml");
    }

    /**
     * The title label of the controller.
     */
    @FXML
    private Label titleLabel;

    /**
     * The list view that is used as a menu.
     */
    @FXML
    protected ListView listView;

    /**
     * The pane containing the listview
     */
    @FXML
    private AnchorPane leftPane;

    /**
     * The pane containing the custom view.
     */
    @FXML
    private AnchorPane rightPane;

    /**
     * The split pane which contains the left and right pane.
     */
    @FXML
    private SplitPane splitPane;

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);

        //apply custom style
        splitPane.setStyle("-fx-background-color: white;");
        leftPane.setStyle("-fx-background-color: white;");
        rightPane.setStyle("-fx-background-color: white;");

        //set title (if sub-classed)
        String title = title();
        if(title != null)
            titleLabel.setText(title);

        //bind listview to listener function
        listView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue)
                -> onListItemChanged(newValue.intValue()));
    }

    /**
     * Use this method to show a UIView on the right pane.
     * @param view
     */
    protected void showView(UIView view){
        //remove all views
        rightPane.getChildren().clear();

        //check that we are not adding null
        if(view != null){
            //add auto layout constraints
            AnchorPane.setTopAnchor(view,8.0);
            AnchorPane.setBottomAnchor(view,8.0);
            AnchorPane.setRightAnchor(view,8.0);
            AnchorPane.setLeftAnchor(view,8.0);

            //add to pane
            rightPane.getChildren().add(view);

            //add delegate
            view.setDelegate(this);
        }
    }

    /**
     * Function that is called when an item on the list gets selected.
     * @param value The index of the item.
     */
    protected void onListItemChanged(int value){ }


}
