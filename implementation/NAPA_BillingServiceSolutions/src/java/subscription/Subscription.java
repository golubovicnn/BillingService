package subscription;

import products.Plan;
import products.Product;
import util.DateUtils;

import javax.print.DocFlavor;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Andreas on 05.06.2017.
 */
public class Subscription {

    private Long validUntil;
    private SubscriptionState state = SubscriptionState.INACTIVE;
    private Plan plan;
    private Product product;
    private String customerId;
    private String invoiceId;

    public Subscription(Long validUntil, SubscriptionState state, Plan plan, Product product, String customerId, String invoiceId) {
        this.validUntil = validUntil;
        this.state = state;
        this.plan = plan;
        this.product = product;
        this.customerId = customerId;
        this.invoiceId = invoiceId;
    }

    public void start(){
        state = SubscriptionState.ACTIVE;
    }

    public void cancel(){
        switch (state){
            case ACTIVE:
                state = SubscriptionState.INACTIVE;
                break;
            case SUSPENDED:
                state = SubscriptionState.INACTIVE;
                break;
            default:
                state = SubscriptionState.INACTIVE;
        }
    }

    /**
     * Renews subscription by one month
     */
    public void renew(){
        switch (state){
            case ACTIVE:
                //set one month in future for current valid until
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(validUntil);
                cal.add(Calendar.MONTH, 1);
                validUntil = cal.getTimeInMillis();
                break;
            case SUSPENDED:
                state = SubscriptionState.ACTIVE;
                Calendar mCal = Calendar.getInstance();
                mCal.add(Calendar.MONTH, 1);
                validUntil = mCal.getTimeInMillis();
                break;
            default:
                state = SubscriptionState.SUSPENDED;
        }


    }

    public boolean update(){
        //check if sub is expired

        Date currentTime = new Date();
        Date validUntilTime = new Date(validUntil);
        if(validUntilTime.before(currentTime) ){
            //subscription is expired
            state = SubscriptionState.SUSPENDED;
            return false;
        }

        return true;
    }

    /**
     *  Returns valid until date
     * @return returns a unix timestamp in millis
     */
    public Long getValidUntil() {
        return validUntil;
    }

    public String getValidUntilAsFormatedDate(){
       return DateUtils.unixTImestampToString(validUntil);
    }

    public SubscriptionState getState() {
        return state;
    }

    public Plan getPlan() {
        return plan;
    }

    public Product getProduct() {
        return product;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
}
