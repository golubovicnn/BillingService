package frontend;

import frontend.manager.FrontendManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import products.Plan;
import products.PlanProductManager;
import products.Product;
import subscription.Subscription;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by golubovic on 12.06.17.
 */
public class ProductsScreenController {

    PlanProductManager manager = PlanProductManager.getInstance();

    ArrayList<Plan> plans = manager.getPlans();
    ArrayList<Product> products = manager.getProducts();





    @FXML
    private Text productOneName;
    @FXML
    private Text productOneDescription;
    @FXML
    private Text planOneName;
    @FXML
    private Text planOneDescription;
    @FXML
    private Text planTwoName;
    @FXML
    private Text planTwoDescription;
    @FXML
    private Text productTwoName;
    @FXML
    private Text productTwoDescription;
    @FXML
    private Text productThreeName;
    @FXML
    private Text productThreeDescription;
    @FXML
    private Text planOneName1;
    @FXML
    private Text planOneDescription1;
    @FXML
    private Text planOneName2;
    @FXML
    private Text planOneDescription2;
    @FXML
    private Text planTwoName1;
    @FXML
    private Text planTwoDescription1;
    @FXML
    private Text planTwoName2;
    @FXML
    private Text planTwoDescription2;


    @FXML
    private Text planName1;
    @FXML
    private Text planDescription1;
    @FXML
    private Text subscription1;
    @FXML
    private Text subscriptionDesc1;
    @FXML
    private Text status1;


    @FXML
    private Text planName11;
    @FXML
    private Text planDescription11;
    @FXML
    private Text subscription11;
    @FXML
    private Text subscriptionDesc11;
    @FXML
    private Text status11;


    @FXML
    private Text planName111;
    @FXML
    private Text planDescription111;
    @FXML
    private Text subscription111;
    @FXML
    private Text subscriptionDesc111;
    @FXML
    private Text status111;

    @FXML
    private Label validUntil;

    @FXML
    private Label validUntil1;

    @FXML
    private Label validUntil11;



    public void initialize(){


        /*
        for(Plan planIt : plan){
            planIt.getName();
        }
        */


        productOneName.setText(products.get(0).getName());
        productOneDescription.setText(products.get(0).getDescription());
        planOneName1.setText(plans.get(0).getName());
        planOneDescription1.setText(plans.get(0).getDescription());
        planTwoName1.setText(plans.get(1).getName());
        planTwoDescription1.setText(plans.get(1).getDescription());

        productTwoName.setText(products.get(1).getName());
        productTwoDescription.setText(products.get(1).getDescription());
        planOneName2.setText(plans.get(0).getName());
        planOneDescription2.setText(plans.get(0).getDescription());
        planTwoName2.setText(plans.get(1).getName());
        planTwoDescription2.setText(plans.get(1).getDescription());

        productThreeName.setText(products.get(2).getName());
        productThreeDescription.setText(products.get(2).getDescription());
        planOneName.setText(plans.get(0).getName());
        planOneDescription.setText(plans.get(0).getDescription());
        planTwoName.setText(plans.get(1).getName());
        planTwoDescription.setText(plans.get(1).getDescription());





        FrontendManager manager = FrontendManager.getInstance();

        ArrayList<Subscription> subscriptions = manager.getAllSubscription();

        if(subscriptions ==  null ||subscriptions.isEmpty()){
            return;
        }

        if(subscriptions.size() > 0){

            planName1.setText(subscriptions.get(0).getPlan().getName());
            planDescription1.setText(subscriptions.get(0).getPlan().getDescription());
            subscription1.setText(subscriptions.get(0).getProduct().getName());
            subscriptionDesc1.setText(subscriptions.get(0).getProduct().getDescription());
            status1.setText(subscriptions.get(0).getState().toString());
            validUntil.setText(subscriptions.get(0).getValidUntilAsFormatedDate());
        }
        if(subscriptions.size() > 1){
            planName11.setText(subscriptions.get(1).getPlan().getName());
            planDescription11.setText(subscriptions.get(1).getPlan().getDescription());
            subscription11.setText(subscriptions.get(1).getProduct().getName());
            subscriptionDesc11.setText(subscriptions.get(1).getProduct().getDescription());
            status11.setText(subscriptions.get(1).getState().toString());
            validUntil1.setText(subscriptions.get(0).getValidUntilAsFormatedDate());
        }
        if(subscriptions.size() > 2){
            planName111.setText(subscriptions.get(2).getPlan().getName());
            planDescription111.setText(subscriptions.get(2).getPlan().getDescription());
            subscription111.setText(subscriptions.get(2).getProduct().getName());
            subscriptionDesc111.setText(subscriptions.get(2).getProduct().getDescription());
            status111.setText(subscriptions.get(2).getState().toString());
            validUntil11.setText(subscriptions.get(0).getValidUntilAsFormatedDate());
        }
    }


    public void subscriptionOneRenewClicked(){
        FrontendManager manager = FrontendManager.getInstance();

        ArrayList<Subscription> subscriptions = manager.getAllSubscription();

        if(subscriptions ==  null ||subscriptions.isEmpty()){
            return;
        }

        if(subscriptions.size() > 0){
            Subscription sub = subscriptions.get(0);
            renewSub(sub);
        }

    }

    public void subscriptionTwoRenewClicked(){
        FrontendManager manager = FrontendManager.getInstance();

        ArrayList<Subscription> subscriptions = manager.getAllSubscription();

        if(subscriptions ==  null ||subscriptions.isEmpty()){
            return;
        }

        if(subscriptions.size() > 0){
            Subscription sub = subscriptions.get(0);
            cancelSub(sub);
        }
    }

    public void subscriptionThreeRenewClicked(){
        FrontendManager manager = FrontendManager.getInstance();

        ArrayList<Subscription> subscriptions = manager.getAllSubscription();

        if(subscriptions ==  null ||subscriptions.isEmpty()){
            return;
        }

        if(subscriptions.size() > 1){
            Subscription sub = subscriptions.get(1);
            renewSub(sub);
        }
    }

    public void subscriptionOneCancelClicked(){

        FrontendManager manager = FrontendManager.getInstance();

        ArrayList<Subscription> subscriptions = manager.getAllSubscription();

        if(subscriptions ==  null ||subscriptions.isEmpty()){

            return;
        }

        if(subscriptions.size() > 0){
            Subscription sub = subscriptions.get(0);
            cancelSub(sub);
        }
    }

    public void subscriptionTwoCancelClicked(){
        FrontendManager manager = FrontendManager.getInstance();

        ArrayList<Subscription> subscriptions = manager.getAllSubscription();

        if(subscriptions ==  null ||subscriptions.isEmpty()){
            return;
        }

        if(subscriptions.size() > 1){
            Subscription sub = subscriptions.get(1);
            renewSub(sub);
        }
    }

    public void subscriptionThreeCancelClicked(){
        FrontendManager manager = FrontendManager.getInstance();

        ArrayList<Subscription> subscriptions = manager.getAllSubscription();

        if(subscriptions ==  null ||subscriptions.isEmpty()){
            return;
        }

        if(subscriptions.size() > 2){
            Subscription sub = subscriptions.get(2);
            cancelSub(sub);
        }
    }

    private void renewSub(Subscription subscription){
        FrontendManager manager = FrontendManager.getInstance();

        manager.renewSubscription(subscription);
    }

    private void cancelSub(Subscription subscription){
        FrontendManager manager = FrontendManager.getInstance();

        manager.cancelSubscription(subscription);
    }


    /**
     * Subscribe Product
     */
    public void productOneStandard()    {
        Product product = products.get(0);
        Plan plan = plans.get(0);
        subscribeProduct(plan, product);
    }
    public void productOnePlus(){
        Product product = products.get(0);
        Plan plan = plans.get(1);
        subscribeProduct(plan, product);
    }
    public void productTwoStandard(){
        Product product = products.get(1);
        Plan plan = plans.get(0);
        subscribeProduct(plan, product);
    }

    public void productTwoPlus(){
        Product product = products.get(1);
        Plan plan = plans.get(0);
        subscribeProduct(plan, product);
    }

    public void productThreeStandard(){
        Product product = products.get(2);
        Plan plan = plans.get(0);
        subscribeProduct(plan, product);
    }

    public void productThreePlus(){
        Product product = products.get(2);
        Plan plan = plans.get(1);
        subscribeProduct(plan, product);
    }

    private void subscribeProduct(Plan plan, Product product){

        //Client client = ClientFactoryProducer.getFactory().createClient();

        FrontendManager frontendManager = FrontendManager.getInstance();

        if(frontendManager.subscribeToPlan(product, plan)){
            //success
            System.out.println("Subscribed");
        }
        else {
            //error
        }

    }

}