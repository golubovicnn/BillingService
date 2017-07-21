package frontend;

import frontend.manager.FrontendManager;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

/**
 * Created by golubovic on 15.06.17.
 */
public class LogInScreenController {
    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField organisationLogIn;

    /**
     * Login submit for Private Customer
     * @throws Exception
     */

    public void clickedSubmit() throws Exception {
        String firstNameEntered = firstname.getText();
        String lastNameEntered = lastname.getText();

        FrontendManager frontendManager = FrontendManager.getInstance();

        if(frontendManager.loginPrivateCustomer(firstNameEntered,lastNameEntered)){
            MainController mainC = new MainController();
            mainC.showProductScreen();
        }
        else {
            //error message
        }

    }

    /**
     * login for Business Customer
     * @throws Exception
     */
    public void clickedSubmitBusiness() throws Exception {
        String organisationEntered = organisationLogIn.getText();

        FrontendManager frontendManager = FrontendManager.getInstance();

        if(frontendManager.loginBusinessCustomer(organisationEntered)){
            MainController mainC = new MainController();
            mainC.showProductScreen();
        }
        else {
            //error message
        }

    }

    /**
     * Go Back to screen bevor
     * @throws Exception
     */
    public void goBack() throws Exception {
        MainController mainC = new MainController();
        mainC.showWelcomeScreen();
    }

}
