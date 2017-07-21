package client.fastbillapi;

/**
 * Created by aldinbradaric on 15/06/17.
 */

import customer.BusinessCustomer;
import customer.Customer;
import customer.PrivateCustomer;
import customer.enumerations.CustomerType;
import customer.enumerations.PaymentType;
import customer.enumerations.SalutationType;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * FastBillCustomer is the remotely saved version of the Customer class.
 * By accessing it (ie. getting it) we can subsequently get the subscriptions associated with it
 */
public class FastBillCustomer implements FastBillDecorator{
    private FastBillRequest mRequest;



    protected FastBillCustomer(FastBillRequest request) {
        mRequest = request;
    }

    /**
     * get all Customers from FastBill-API and put it in an Arraylist
     * @return
     * @throws IllegalArgumentException
     */

    @Override
    public ArrayList<Object> getAll() throws IllegalArgumentException{
        mRequest.getAll();

        System.out.println("doing this");

        try {
            //set entity
            StringEntity mEntity = new StringEntity(customerGetString(""), ContentType.APPLICATION_JSON);
            mRequest.mPost.setEntity(mEntity);

            //actual request
            HttpResponse mResponse = mRequest.mClient.execute(mRequest.mPost);
            HttpEntity mEnt = mResponse.getEntity();
            String response = EntityUtils.toString(mEnt, "UTF-8");

            //check whether connection was valid
            if (mResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new IllegalArgumentException();
            }

            JSONObject mJSON = mRequest.parseJSON(response);
            Object JSONresp = mJSON.get("RESPONSE");
            mJSON = mRequest.parseJSON(JSONresp.toString());
            //get all the saved customers
            JSONArray mJSONArray = (mJSON.getJSONArray("CUSTOMERS"));

            ArrayList<Object> customers = new ArrayList<>(mJSONArray.length());
            //create local customer objects from data
            for (Object jObject : mJSONArray) {
                JSONObject mJSONObj = mRequest.parseJSON(jObject.toString());

                //parse all the values and create a private customer
                if (CustomerType.getCustomerType(mJSONObj.get("CUSTOMER_TYPE").toString()) == CustomerType.CONSUMER) {
                    String customerFirstName = mJSONObj.get("FIRST_NAME").toString();
                    String customerLastName = mJSONObj.get("LAST_NAME").toString();
                    PaymentType customerPaymentType = PaymentType.getPaymentType(Integer.parseInt(mJSONObj.get("PAYMENT_TYPE").toString()));
                    String customerAddress = mJSONObj.get("ADDRESS").toString();
                    String customerTitle = mJSONObj.get("ACADEMIC_DEGREE").toString();
                    SalutationType customerSalutation = SalutationType.getSalutationType(mJSONObj.get("SALUTATION").toString());
                    String customerNumber = mJSONObj.get("CUSTOMER_ID").toString();
                    customers.add(new PrivateCustomer(CustomerType.CONSUMER,customerFirstName, customerLastName,customerTitle,customerSalutation,customerAddress,customerPaymentType,customerNumber));
                }
                else{//parse all the values and create a business customer
                    String organization = mJSONObj.get("ORGANIZATION").toString();
                    PaymentType customerPaymentType = PaymentType.getPaymentType(Integer.parseInt(mJSONObj.get("PAYMENT_TYPE").toString()));
                    String customerAddress = mJSONObj.get("ADDRESS").toString();
                    String customerNumber = mJSONObj.get("CUSTOMER_ID").toString();
                    customers.add(new BusinessCustomer(CustomerType.BUSINESS,organization,customerAddress,customerPaymentType, customerNumber));
                }
            }
            return customers;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * prepare the Jason-Oject for Request by CustomerID
     * @param customerRemoteId
     * @return
     */

    private String customerGetString(String customerRemoteId){
        if(customerRemoteId == null || customerRemoteId.isEmpty()){
            return new JSONObject().put("SERVICE", "customer.get").put("FILTER", "").toString();
        }
        else{
            return new JSONObject().put("SERVICE", "customer.get").put("FILTER", customerRemoteId).toString();
        }

    }

    /**
     * Create a Customer on Fastbill
     * @param object
     */

    @Override
    public void create(Object object){
        if(object == null) return;
        try{

            Customer customer = (Customer)object;

            mRequest.create(object);

            JSONObject requestBody = new JSONObject();
            requestBody.put("SERVICE", "customer.create");

            JSONObject requestBodyData = new JSONObject();
            requestBodyData.put("CUSTOMER_TYPE", customer.getCustomerType().toString());
            requestBodyData.put("PAYMENT_TYPE", customer.getPaymentType().getValue());
            requestBodyData.put("ADDRESS", customer.getAddress());
            switch (customer.getCustomerType()){
                case CONSUMER:
                    PrivateCustomer pCustomer = (PrivateCustomer) customer;
                    requestBodyData.put("FIRST_NAME", pCustomer.getFirstName());
                    requestBodyData.put("LAST_NAME", pCustomer.getLastName());
                    requestBodyData.put("ACADEMIC_DEGREE", pCustomer.getTitle());
                    requestBodyData.put("SALUTATION", pCustomer.getSalutation().toString());
                    break;
                case BUSINESS:
                    BusinessCustomer bCustomer = (BusinessCustomer) customer;
                    requestBodyData.put("ORGANIZATION", bCustomer.getOrganization());
                    break;
                default:
            }

            requestBody.put("DATA", requestBodyData);

           System.out.println(requestBody.toString(4));

            StringEntity mEntity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON);

            mRequest.mPost.setEntity(mEntity);

            //actual request
            HttpResponse mResponse = mRequest.mClient.execute(mRequest.mPost);
            HttpEntity mEnt = mResponse.getEntity();
            String response = EntityUtils.toString(mEnt, "UTF-8");

            //check whether connection was valid
            if (mResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new IllegalArgumentException();
            }

            JSONObject mJSON = mRequest.parseJSON(response);
            Object JSONresp = mJSON.get("RESPONSE");
            mJSON = mRequest.parseJSON(JSONresp.toString());
            //get all the saved customers
            String customer_id = mJSON.get("CUSTOMER_ID").toString();

            customer.setRemoteId(customer_id);



        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Get Customer by ID from Fastbill
     * @param id
     * @return
     */

    @Override
    public Object getById(String id) {

        ArrayList<Object> objects = getAll();

        if (objects == null) return null;

        ArrayList<Customer> customers = objects.stream().map(customer -> (Customer) customer).collect(Collectors.toCollection(ArrayList::new));

        for(Customer customer : customers){
            if(customer.getRemoteId().equals(id)){
                return customer;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {

    }

    private void parseJO() {
        /*HttpEntity mEnt = mResponse.getEntity();
        String response = EntityUtils.toString(mEnt, "UTF-8");

        //the JSON object that's returned consists of all the saved customers
        JSONObject mJSON = parseJSON(response);
        Object JSONresp = mJSON.get("RESPONSE");
        mJSON = parseJSON(JSONresp.toString());
        System.out.println("Parsing customers");
        JSONArray mJSONArray = (mJSON.getJSONArray("CUSTOMERS"));

        //the array consisting of customers is being processed one by one
        for (Object jObject : mJSONArray) {
            JSONObject mJSONObj = parseJSON(jObject.toString());
            String customerType = mJSONObj.get("CUSTOMER_TYPE").toString();
            CustomerType mCust = CustomerType.getCustomerType(customerType);
            System.out.println(mCust.name());
        }*/
    }


}
