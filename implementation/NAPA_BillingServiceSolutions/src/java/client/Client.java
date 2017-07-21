package client;

import customer.Customer;
import products.Plan;
import products.Product;
import subscription.Subscription;

import java.util.ArrayList;

/**
 * Created by aldinbradaric on 04/06/17.
 */

/**
 * Interface for Client with abstract Methodes
 */
public interface Client {

    public ArrayList<Customer> getAllCustomers();

    public boolean createCustomer(Customer customer) throws IllegalArgumentException ;

    public Customer getCustomerById(String id);

    //Article functions



    public void createArticleFromPlanAndProduct(Plan plan, Product product);

    public boolean getProductAndPlanByArticleId(String id, Plan outPlan, Product outProduct);

    public boolean getAllProductsAndPlans(ArrayList<Plan> outPlan, ArrayList<Product> outProduct);

    //Subscriptions methods



    public void createSubscription(Subscription subscription) ;

    public ArrayList<Subscription> getAllSubscription() ;

    public ArrayList<Subscription> getAllSubscriptionsByCustomerId(String id);

    public boolean cancelSubscription(String id);
}
