package client;

import customer.Customer;
import products.Plan;
import products.PlanProductManager;
import products.Product;
import subscription.Subscription;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by aldinbradaric on 04/06/17.
 */
public class MockUpClient implements Client {


    protected MockUpClient() {}


    /**
     * get all Customers from serialized File
     * @return
     */


    @Override
    public ArrayList<Customer> getAllCustomers() {

        ArrayList<Customer> customerArrayList;

        try {

            File file = new File("//customer.ser");

            if (!file.exists()) return null;

            FileInputStream dateiInStream = new FileInputStream(file);
            ObjectInputStream objektInputStream = new ObjectInputStream(dateiInStream);

            customerArrayList = (ArrayList<Customer>) objektInputStream.readObject();

            objektInputStream.close();
            dateiInStream.close();

            return customerArrayList;

        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        } catch (ClassCastException ex){
            ex.printStackTrace();
            return null;
        }



    }


    /**
     * serialized a new Customer
     * @param customer
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public boolean createCustomer(Customer customer) throws IllegalArgumentException {

        File file = new File("//customer.ser");
        if (!file.exists()) {


            try {

                file.createNewFile();


               ArrayList<Customer> customerArrayList = new ArrayList<>();

                customerArrayList.add(customer);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objektOutputStream = new ObjectOutputStream(fileOutputStream);


                objektOutputStream.writeObject(customerArrayList);

                objektOutputStream.close();
                fileOutputStream.close();

                return true;


            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {

                ArrayList<Customer> customerArrayList = getAllCustomers();
                customerArrayList.add(customer);




                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objektOutputStream = new ObjectOutputStream(fileOutputStream);


                objektOutputStream.writeObject(customerArrayList);

                objektOutputStream.close();
                fileOutputStream.close();

                return true;

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


            return false;


    }

    /**
     * deserialize Customers from File an pick out Customer by ID
     * @param id
     * @return
     */

    @Override
    public Customer getCustomerById(String id) {
        ArrayList<Customer> customerArrayList;
        customerArrayList = getAllCustomers();

        Iterator<Customer> customerIterator = customerArrayList.iterator();
        while (customerIterator.hasNext()){
            Customer temp = customerIterator.next();
            if(temp.getRemoteId() == id){
                System.out.println("Customer-Objekt gefunden!");
                return temp;

            }
        }

        return null;
    }

    /**
     * Create a new Articel
     * @param plan
     * @param product
     */

    @Override
    public void createArticleFromPlanAndProduct(Plan plan, Product product) {

        File file = new File("//plan.ser");
        if (!file.exists()) {


            try {

                file.createNewFile();


                ArrayList<Plan> planArrayList = new ArrayList<>();

                planArrayList.add(plan);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objektOutputStream = new ObjectOutputStream(fileOutputStream);


                objektOutputStream.writeObject(planArrayList);

                objektOutputStream.close();
                fileOutputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {

                ArrayList<Plan> planArrayList = getAllPlan();
                planArrayList.add(plan);




                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objektOutputStream = new ObjectOutputStream(fileOutputStream);


                objektOutputStream.writeObject(planArrayList);

                objektOutputStream.close();
                fileOutputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }




    }

    public ArrayList<Plan> getAllPlan(){
       return null;
    }


    /**
     * Get Artcel by ID
     * @param id
     * @param outPlan
     * @param outProduct
     * @return
     */

    @Override
    public boolean getProductAndPlanByArticleId(String id, Plan outPlan, Product outProduct) {
        if(id.equals("1") ) {
            outPlan = PlanProductManager.getInstance().getPlanByPrice(20);
            outProduct = PlanProductManager.getInstance().getProductByNumber(1);

        }else{
            outPlan = PlanProductManager.getInstance().getPlanByPrice(50);
            outProduct = PlanProductManager.getInstance().getProductByNumber(2);

        }

        return true;
    }

    /**
     * create Products and Plans
     * @param outPlan
     * @param outProduct
     * @return
     */
    @Override
    public boolean getAllProductsAndPlans(ArrayList<Plan> outPlan, ArrayList<Product> outProduct) {

       outPlan = PlanProductManager.getInstance().getPlans();
       outProduct = PlanProductManager.getInstance().getProducts();


        return true;
    }

    /**
     * serialize Subscription
     * @param subscription
     */
    @Override
    public void createSubscription(Subscription subscription) {

        File file = new File("//subscription.ser");
        if (!file.exists()) {


            try {

                file.createNewFile();


                ArrayList<Subscription> subscriptionArrayList = new ArrayList<>();

                subscriptionArrayList.add(subscription);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objektOutputStream = new ObjectOutputStream(fileOutputStream);


                objektOutputStream.writeObject(subscriptionArrayList);

                objektOutputStream.close();
                fileOutputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {

                ArrayList<Subscription> subscriptionArraylist = getAllSubscription();
                subscriptionArraylist.add(subscription);


                subscriptionArraylist.add(subscription);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objektOutputStream = new ObjectOutputStream(fileOutputStream);


                objektOutputStream.writeObject(subscriptionArraylist);

                objektOutputStream.close();
                fileOutputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    /**
     * deserialize all Subscription and put it in an Arraylist
     * @return
     */

    @Override
    public ArrayList<Subscription> getAllSubscription() {

        ArrayList<Subscription> subscriptionArrayList;

        try {

            File file = new File("//subscription.ser");

            if (!file.exists()) return null;

            FileInputStream dateiInStream = new FileInputStream(file);
            ObjectInputStream objektInputStream = new ObjectInputStream(dateiInStream);

            subscriptionArrayList = (ArrayList<Subscription>) objektInputStream.readObject();

            objektInputStream.close();
            dateiInStream.close();

            return subscriptionArrayList;

        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        } catch (ClassCastException ex){
            ex.printStackTrace();
            return null;
        }

    }

    /**
     * get all Subscription from one Customer
     * @param id
     * @return
     */

    @Override
    public ArrayList<Subscription> getAllSubscriptionsByCustomerId(String id) {
        ArrayList<Subscription> subscriptionArrayList = getAllSubscription();
        ArrayList<Subscription> selctedSubList = new ArrayList<>();
        Iterator<Subscription> subscriptionIterator = subscriptionArrayList.iterator();

        while (subscriptionIterator.hasNext()){
            Subscription temp = subscriptionIterator.next();
            if(temp.getCustomerId() == id){
                selctedSubList.add(temp);
            }
        }

        return selctedSubList;
    }


    /**
     * cancle Subscription by ID
     * @param id
     * @return
     */
    @Override
    public boolean cancelSubscription(String id) {

        return false;
    }
}
