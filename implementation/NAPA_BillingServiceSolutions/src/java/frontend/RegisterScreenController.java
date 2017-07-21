package frontend;

import customer.enumerations.PaymentType;
import customer.enumerations.SalutationType;
import frontend.manager.FrontendManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Created by golubovic on 12.06.17.
 *
 * This class controlls actions at the Register Screen.
 */
public class RegisterScreenController {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField degree;

    @FXML
    private ChoiceBox choiceBoxTitel;

    @FXML
    private TextField address;

    @FXML
    private ChoiceBox choiceBoxPayment;

    //Business Tab
    @FXML
    private TextField organisation;

    @FXML
    private TextField addressBusiness;

    @FXML
    private ChoiceBox choiceBoxPaymentBusiness;

    /**
     * Set up all ChoiceBoxes
     */
    public void initialize(){

        ObservableList<String> choiceBoxTitelStrings = FXCollections.observableArrayList();
        for (SalutationType salutationType : SalutationType.values()) {
            choiceBoxTitelStrings.add(salutationType.name());
        }

        ObservableList<String> choiceBoxPaymentStrings = FXCollections.observableArrayList();
        for(PaymentType paymentType : PaymentType.values()) {
            choiceBoxPaymentStrings.add(paymentType.name());
        }

        choiceBoxTitel.setValue(SalutationType.MR.toString());
        choiceBoxTitel.setItems(choiceBoxTitelStrings);

        choiceBoxPayment.setValue(PaymentType.CASH.toString());
        choiceBoxPayment.setItems(choiceBoxPaymentStrings);

        choiceBoxPaymentBusiness.setValue(PaymentType.CASH.toString());
        choiceBoxPaymentBusiness.setItems(choiceBoxPaymentStrings);
    }

    /**
     * Takes all entered Values from a Private Tab
     */
    public void privateSignUpClicked(){
        String firstNameEntered = firstName.getText();
        String lastNameEntered = lastName.getText();
        String degreeEntered = degree.getText();
        String selectedTitel = choiceBoxTitel.getSelectionModel().getSelectedItem().toString();
        String addresEntered = address.getText();
        String selectedPayment = choiceBoxPayment.getSelectionModel().getSelectedItem().toString();

        System.out.println("firstName=" + firstNameEntered + "\nlastName=" + lastNameEntered +
                "\ndegree=" + degreeEntered + "\nTitel=" + selectedTitel + "\naddress=" + addresEntered + "\nPayment=" + selectedPayment);


        FrontendManager frontendManager = FrontendManager.getInstance();

        if(frontendManager.signupPrivateCustomer(firstNameEntered,lastNameEntered,degreeEntered,SalutationType.getSalutationType(selectedTitel), addresEntered, PaymentType.CASH)){
            MainController mScreen = new MainController();
            try {
                mScreen.showProductScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            //show error message
        }


    }

    /**
     * Takes all entered Values from a Business Tab
     */
    public void businessSignUpClicked() throws Exception {
        String organisationName = organisation.getText();
        String addressBusinessEntered = addressBusiness.getText();
        String selectedPayment = choiceBoxPaymentBusiness.getSelectionModel().getSelectedItem().toString();
        //CustomerType customerType = CustomerType.BUSINESS;

        System.out.println("organisationName="+organisationName+"\naddress=" + addressBusinessEntered + "\nPayment=" + selectedPayment);

        FrontendManager frontendManager = FrontendManager.getInstance();

        if(frontendManager.signupBusinessCustomer(organisationName,addressBusinessEntered,PaymentType.CASH)){
            MainController mainC = new MainController();
            mainC.showProductScreen();
        }
        else{
            //show some error message
        }

    }

    public void goBack() throws Exception {
        MainController mainC = new MainController();
        mainC.showWelcomeScreen();
    }


}