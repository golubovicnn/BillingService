package client.fastbillapi;

/**
 * Created by aldinbradaric on 15/06/17.
 */

import client.Client;
import customer.Customer;
import products.Plan;
import products.Product;
import subscription.Subscription;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class is the only one being accessed by the user and implements the proxy pattern
 */
public class FastBillClient implements Client {

    private String apiKey;
    private String apiEmail;

    /**
     * Constructor for Client
     * @param apiKey
     * @param apiEmail
     */

    public FastBillClient(String apiKey, String apiEmail) {
        this.apiEmail = apiEmail;
        this.apiKey = apiKey;
    }


    /**
     * get all Customers from FastBill-API and put it in an Arraylist
     * @return
     */

    public ArrayList<Customer> getAllCustomers(){

        FastBillRequest request = new FastBillRequest();
        FastBillCustomer customerRequest = new FastBillCustomer(request);

        ArrayList<Object> customers = customerRequest.getAll();

        if(customers != null){
            return customers.stream().map(customer -> (Customer) customer).collect(Collectors.toCollection(ArrayList::new));
        }

        return null;
    }

    /**
     * CreatCustomer prepare an Creat-Request to create a new Customer on FastBill
     * @param customer
     * @return
     * @throws IllegalArgumentException
     */

    public boolean createCustomer(Customer customer) throws IllegalArgumentException {

        FastBillRequest request = new FastBillRequest();
        FastBillCustomer customerRequest = new FastBillCustomer(request);

        try{
            customerRequest.create(customer);
        }
        catch (Exception e){
            return false;
        }

        return true;
    }

    /**
     * Getter Customer by ID
     * @param id
     * @return
     */

    public Customer getCustomerById(String id) {
        return null;
    }

    //Article functions


    /**
     * Create a new Article with Plan and Product on Fastbill with an prepared Request
     * @param plan
     * @param product
     */


    public void createArticleFromPlanAndProduct(Plan plan, Product product){
        if(plan == null ||product == null){
            return;
        }

        FastBillArticleModel article = new FastBillArticleModel(plan.getName(),product.getNumber(),plan.getDescription(),plan.getMonthlyRate());

        FastBillRequest request = new FastBillRequest();
        FastBillArticle articleRequest = new FastBillArticle(request);


        articleRequest.create(article);

    }

    /**
     * Get Articel from FastBill
     * @param id
     * @param outPlan
     * @param outProduct
     * @return
     */

    public boolean getProductAndPlanByArticleId(String id, Plan outPlan, Product outProduct) {
        return false;
    }

    /**
     * Get all Produkts and Plans form Fastbill and put it in an Arraylist
     * @param outPlan
     * @param outProduct
     * @return
     */

    public boolean getAllProductsAndPlans(ArrayList<Plan> outPlan, ArrayList<Product> outProduct) {

        if(outPlan == null ||outProduct == null){
            return false;
        }

        FastBillRequest request = new FastBillRequest();
        FastBillArticle articleRequest = new FastBillArticle(request);


        ArrayList<Object> objects = articleRequest.getAll();

        ArrayList<FastBillArticleModel> articles = objects.stream().map(customer -> (FastBillArticleModel) customer).collect(Collectors.toCollection(ArrayList::new));

        for(FastBillArticleModel article : articles){
        /*    Plan plan1
            outPlan.add()
                    */
        }


        return false;
    }

    //Subscriptions methods

    /**
     * Create a Subscription
     * @param subscription
     */


    public void createSubscription(Subscription subscription) {
        if(subscription == null){
            return;
        }

        FastBillRequest request = new FastBillRequest();
        FastBillSubscription subscriptionRequest = new FastBillSubscription(request);

        subscriptionRequest.create(subscription);
    }

    /**
     * Get all Subscription
     * @return
     */

    @Override
    public ArrayList<Subscription> getAllSubscription() {
        return null;
    }

    /**
     * Get Subscription form Fastbill with CustomerId
     * @param id
     * @return
     */

    public ArrayList<Subscription> getAllSubscriptionsByCustomerId(String id) {

        if(id == null || id.isEmpty()){
            return null;
        }

        FastBillRequest request = new FastBillRequest();
        FastBillSubscription subscriptionRequest = new FastBillSubscription(request);


        ArrayList<Object> objects = subscriptionRequest.getAll();



        ArrayList<Subscription> subs = objects.stream().map(object -> (Subscription) object).collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Subscription> customerSubs = new ArrayList<>();
        for(Subscription sub : subs){
            if(!sub.getCustomerId().equals(id)){
                customerSubs.add(sub);
            }
        }

        return customerSubs;
    }

    /**
     * Cancel Subscription by ID
     * @param id
     * @return
     */
    public boolean cancelSubscription(String id) {


        if(id == null || id.isEmpty()){
            return false;
        }

        System.out.println("canceling in client");
        FastBillRequest request = new FastBillRequest();
        FastBillSubscription subscriptionRequest = new FastBillSubscription(request);

        subscriptionRequest.delete(id);

        return true;
    }
}
