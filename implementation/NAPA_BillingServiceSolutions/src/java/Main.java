
import frontend.MainController;

import client.fastbillapi.FastBillRequest;
import client.Client;
import client.ClientFactoryProducer;
import customer.PrivateCustomer;
import customer.enumerations.CustomerType;
import customer.enumerations.PaymentType;
import customer.enumerations.SalutationType;

/**
 * Created by Andreas on 29.05.2017.
 */
public class Main {


    public static void main(String args[]){
        /*
        try{
                PlanNotFoundExceptionThrower planNotFoundExceptionThrower = new PlanNotFoundExceptionThrower();
                planNotFoundExceptionThrower.doSomethingThatCanThrowAnException(true);
        }
        catch (PlanNotFoundException p){
            p.getMessage();
            System.out.println(p.getMessage());
        }
        catch (NullPointerException e){
        }
        */
     /*   Client client = ClientFactoryProducer.getFactory().createClient();

        System.out.println(client.getClass());

        System.out.println(PaymentType.TRANSFER.getValue());
        System.out.println(PaymentType.TRANSFER);


        Post mPost = new Post();
        mPost.postProduct();
*/

        MainController.launch(MainController.class);

        System.out.println(CustomerType.BUSINESS);


        System.out.println(CustomerType.BUSINESS);

        //System.out.println(client.getAllSubscriptionsByCustomerId("3833558"));


        //System.out.println(client.createCustomer(new PrivateCustomer(CustomerType.CONSUMER,"asdas", "adsa", "asdas", SalutationType.MR, "asda", PaymentType.CASH,"asdasd")));
    }
}


