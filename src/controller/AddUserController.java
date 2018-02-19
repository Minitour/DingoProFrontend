package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Account;
import model.OperationalOfficer;
import ui.UIView;
import ui.UIViewController;
import view.AccountFormView;
import view.OfficerFormView;
import view.UIFormView;

/**
 * Created By Tony on 14/02/2018
 */
public class AddUserController extends UIViewController {

    @FXML
    private Label form_title;

    @FXML
    private VBox formHolder;

    @FXML
    private Button submit;

    private AccountFormView accountFormView = new AccountFormView();

    private OfficerFormView officerFormView = new OfficerFormView();

    private UserType type;

    private UIFormView[] forms;

    public AddUserController(UserType type) {
        super("/resources/xml/controller_add_user.fxml");
        init(type);

        submit.setOnAction(event -> submit());
        form_title.setText("Create "+type.value);
    }

    private void init(UserType type){
        this.type = type;
        cleanFormHolder();
        addViewToStack(accountFormView);

        switch (type){
            case OPERATIONAL:
                addViewToStack(officerFormView);
                break;
        }

        forms = new UIFormView[]{accountFormView,officerFormView};

    }

    private void submit(){

        for(UIFormView form : getRelevantFroms()){
            if (!form.isValid())
                return;
        }

        Account account = null;

        //account
        String email = accountFormView.getEmail();
        String password = accountFormView.getPassword();

        //officer
        String badgeNum;
        String phoneExt;
        String officer_name;
        int rank;

        switch (type){
            case SUPERUSER:
                account = new Account(email,0,password);
                break;
            case HIGHRANK:
                account = new Account(email,1,password);
                break;
            case ONCALL:
                account = new Account(email,3,password);
                break;
            case OPERATIONAL:
                badgeNum = officerFormView.getBadgeNum();
                phoneExt = officerFormView.getPhoneExt();
                officer_name = officerFormView.getName();
                rank = officerFormView.getRank();
                account = new OperationalOfficer(null,email,password,badgeNum,officer_name,phoneExt,rank);
                break;
        }
        onAccountReady(account);
    }

    private UIFormView[] getRelevantFroms(){
        switch (type){
            case OPERATIONAL:
                return new UIFormView[]{officerFormView,accountFormView};
            default:
                return new UIFormView[]{accountFormView};
        }
    }

    public void onAccountReady(Account account){

    }

    private void addViewToStack(UIView view){
        if(view == null)
            return;
        formHolder.getChildren().add(view);
    }

    private void cleanFormHolder(){
        formHolder.getChildren().clear();
    }

    public boolean isValid() {
        switch (type){
            case OPERATIONAL:
                return accountFormView.isValid() && officerFormView.isValid();
            default:
                return accountFormView.isValid();
        }
    }

    public void reset() {
        accountFormView.reset();

        officerFormView.reset();
    }


    public enum UserType{
        SUPERUSER("Superuser",0),
        HIGHRANK("High Rank Officer",1),
        OPERATIONAL("Operational Officer",2),
        ONCALL("On Call Officer",3);


        private String value;
        private int role;

        UserType(String value, int role) {
            this.value = value;
            this.role = role;
        }

        @Override
        public String toString() {
            return value;
        }

        public int getRole() {
            return role;
        }
    }

    public AccountFormView getAccountFormView() {
        return accountFormView;
    }
}
