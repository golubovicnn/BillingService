package client.fastbillapi;

/**
 * Created by aldinbradaric on 15/06/17.
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import products.Plan;
import products.PlanProductManager;
import products.Product;
import subscription.Subscription;
import subscription.SubscriptionState;
import util.DateUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * FastBillSubscription is saved as 'recurring' on FastBill and its job is
 * to represent the subscription object when a customer picks a plan for a particular product
 */
public class FastBillSubscription implements FastBillDecorator{

    private FastBillRequest mRequest;

    protected FastBillSubscription(FastBillRequest request) {
        mRequest = request;
    }

    @Override
    public ArrayList<Object> getAll() {
        mRequest.getAll();


        return getAll("");
    }

    @Override
    public void create(Object object) {

        mRequest.create(object);

        if(object == null){
            return;
        }

        try {

            Subscription subscription = (Subscription) object;

            JSONObject returnBody = new JSONObject();
            returnBody.put("SERVICE", "invoice.create");

            JSONObject returnBodyContent = new JSONObject();

            returnBodyContent.put("CUSTOMER_ID", subscription.getCustomerId());
            returnBodyContent.put("DUE_DATE", subscription.getValidUntilAsFormatedDate());

            JSONArray JSONItems = new JSONArray();
            JSONObject item = new JSONObject();
            item.put("ARTICLE_NUMBER", subscription.getProduct().getNumber());
            item.put("DESCRIPTION", subscription.getProduct().getDescription());
            item.put("UNIT_PRICE", subscription.getPlan().getMonthlyRate());
            item.put("VAT_PERCENT", "20");
            JSONItems.put(item);

            returnBodyContent.put("ITEMS", JSONItems);
            returnBody.put("DATA", returnBodyContent);


            StringEntity mEntity = new StringEntity(returnBody.toString(), ContentType.APPLICATION_JSON);
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


            subscription.setInvoiceId(mJSON.get("INVOICE_ID").toString());

            System.out.println(subscription);

        }catch (Exception e){
            System.out.println("error subscribing");
        }
    }

    @Override
    public Object getById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

        mRequest.delete(id);

        try {

            JSONObject returnBody = new JSONObject();
            returnBody.put("SERVICE", "invoice.delete");

            JSONObject returnBodyContent = new JSONObject();
            returnBodyContent.put("INVOICE_ID", id);
            returnBody.put("DATA", returnBodyContent);


            StringEntity mEntity = new StringEntity(returnBody.toString(), ContentType.APPLICATION_JSON);
            mRequest.mPost.setEntity(mEntity);

            //actual request
            HttpResponse mResponse = mRequest.mClient.execute(mRequest.mPost);
            System.out.println("deleted");

        }
        catch (Exception e){
            System.out.println("failed deleting");
        }
    }

    private SubscriptionState parseSubscriptionState(String validUntil, String paidDate){

        Date dueDate = DateUtils.parseDate(validUntil);
        Date payDate = DateUtils.parseDate(paidDate);

        if(dueDate.before(payDate)){
            return SubscriptionState.ACTIVE;
        }
        else {
            return SubscriptionState.SUSPENDED;
        }


    }

    public ArrayList<Object> getAll(String Id){

        try{

            JSONObject returnBody = new JSONObject();
            returnBody.put("SERVICE", "invoice.get");

            if(Id == null || Id.isEmpty()){
                returnBody.put("FILTER","");
            }
            else{
                returnBody.put("FILTER",Id);
            }

            StringEntity mEntity = new StringEntity(returnBody.toString(), ContentType.APPLICATION_JSON);
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

            //get all the saved articles
            JSONArray mJSONArray = (mJSON.getJSONArray("INVOICES"));

            ArrayList<Object> subs = new ArrayList<>(mJSONArray.length());
            //create local article objects from data
            for (Object jObject : mJSONArray) {
                JSONObject mJSONObj = mRequest.parseJSON(jObject.toString());
                String customerId = mJSONObj.get("CUSTOMER_ID").toString();
                String paidDate = mJSONObj.get("PAID_DATE").toString();

                String validUntil = mJSONObj.get("DUE_DATE").toString();

                String invoiceId = mJSONObj.get("INVOICE_ID").toString();

                SubscriptionState subState = parseSubscriptionState(validUntil, paidDate);


                Date nDate = DateUtils.parseDate(validUntil);

                JSONArray jsonArray = mJSONObj.getJSONArray("ITEMS");
                FastBillArticleModel article = FastBillArticleModel.createArticleModelFromJSON(new JSONObject(jsonArray.get(0).toString()));

                Plan plan = PlanProductManager.getInstance().getPlanByPrice(article.getUnitPrice());
                Product product = PlanProductManager.getInstance().getProductByNumber(article.getArticleNumber());

                subs.add(new Subscription(nDate.getTime(), SubscriptionState.ACTIVE, plan, product, customerId, invoiceId ));
            }

            return subs;

        }catch (Exception e){
            System.out.println("error subscrbing");
            System.out.println(e.getMessage());
        }

        System.out.println("returning null");
        return null;
    }

}
