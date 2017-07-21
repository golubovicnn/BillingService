package frontend.manager;

import client.Client;
import client.ClientFactoryProducer;
import customer.BusinessCustomer;
import customer.Customer;
import customer.PrivateCustomer;
import customer.enumerations.CustomerType;
import customer.enumerations.PaymentType;
import customer.enumerations.SalutationType;
import org.omg.PortableInterceptor.SUCCESSFUL;
import products.Plan;
import products.Product;
import subscription.Subscription;
import subscription.SubscriptionState;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Andreas on 18.06.2017.
 */
public class FrontendManager {

    private static FrontendManager instance = new FrontendManager();
    private static Customer currentLoggedInCustomer = null;

    private FrontendManager(){}

    public static FrontendManager getInstance(){
        return instance;
    }

    public Customer getCurrentLoggedInCustomer(){
        return currentLoggedInCustomer;
    }

    public boolean loginPrivateCustomer(String firstName, String lastName){

        Client client = ClientFactoryProducer.getFactory().createClient();

        ArrayList<Customer> customers = client.getAllCustomers();

        if(customers == null || customers.isEmpty() ){
            return false;
        }

        for(Customer customer : customers){
            try{
                PrivateCustomer privateCustomer = (PrivateCustomer) customer;
                if(privateCustomer.getFirstName().equals(firstName) && privateCustomer.getLastName().equals(lastName)){
                    currentLoggedInCustomer = customer;
                    System.out.println(currentLoggedInCustomer.getRemoteId());
                    return true;
                }
            }catch (ClassCastException c){
                //ignore this customer, because it´s not a private one
            }
        }
        return false;
    }

    public boolean loginBusinessCustomer(String organisation){

        Client client = ClientFactoryProducer.getFactory().createClient();

        ArrayList<Customer> customers = client.getAllCustomers();

        if(customers == null || customers.isEmpty() ){
            return false;
        }

        for(Customer customer : customers){
            try{
                BusinessCustomer businessCustomer = (BusinessCustomer) customer;
                if(businessCustomer.getOrganization().equals(organisation)){
                    currentLoggedInCustomer = customer;
                    System.out.println(currentLoggedInCustomer.getRemoteId());
                    return true;
                }
            }catch (ClassCastException c){
                //ignore this customer, because it´s not a private one
            }
        }
        return false;
    }

    public boolean signupPrivateCustomer(String firstName, String lastName, String title, SalutationType salutationType, String address, PaymentType paymentType){

        Client client = ClientFactoryProducer.getFactory().createClient();

        final PrivateCustomer privateCustomer = new PrivateCustomer(CustomerType.CONSUMER, firstName,lastName,title,salutationType,address,PaymentType.CASH, "123");

       if( client.createCustomer(privateCustomer)){
           currentLoggedInCustomer = privateCustomer;

           System.out.println(currentLoggedInCustomer.getRemoteId());

            return true;
       }

        return false;
    }

    public boolean signupBusinessCustomer(String organisation, String address, PaymentType paymentType){

        Client client = ClientFactoryProducer.getFactory().createClient();

        final BusinessCustomer businessCustomer = new BusinessCustomer(CustomerType.BUSINESS, organisation,address,PaymentType.CASH, "123");

        if(client.createCustomer(businessCustomer)){
            currentLoggedInCustomer = businessCustomer;

            System.out.println(currentLoggedInCustomer.getRemoteId());

            return true;
        }


        return false;
    }

    public boolean subscribeToPlan(Product product, Plan plan){

        Client client = ClientFactoryProducer.getFactory().createClient();

        Date date = new Date();
        Long validUntil = date.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(validUntil);
        cal.add(Calendar.MONTH, 1);
        validUntil = cal.getTimeInMillis();

        Subscription subscription = new Subscription(validUntil, SubscriptionState.ACTIVE, plan, product, currentLoggedInCustomer.getRemoteId(), "123");

        client.createSubscription(subscription);

        System.out.println(subscription.getInvoiceId());

        return true;
    }

    public ArrayList<Subscription> getAllSubscription(){
        Client client = ClientFactoryProducer.getFactory().createClient();

        return client.getAllSubscriptionsByCustomerId(currentLoggedInCustomer.getRemoteId());
    }

    public boolean cancelSubscription(Subscription subscription){
        Client client = ClientFactoryProducer.getFactory().createClient();

        System.out.println("canceling");
        if(client.cancelSubscription(subscription.getInvoiceId())){
            System.out.println("canceled sub");
            return true;
        }

        return false;
    }

    public boolean renewSubscription(Subscription subscription){
        Client client = ClientFactoryProducer.getFactory().createClient();

        subscription.renew();
        if(client.cancelSubscription(subscription.getInvoiceId())){
            client.createSubscription(subscription);
            System.out.println("renewed sub");
            return true;
        }
        return false;
    }

    public boolean payInvoice(Subscription subscription){

        return true;
    }

}
