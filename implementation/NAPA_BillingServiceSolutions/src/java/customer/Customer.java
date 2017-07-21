package customer;

import customer.enumerations.CustomerType;
import customer.enumerations.PaymentType;
import subscription.Subscription;
import java.util.ArrayList;

/**
 * Created by aldinbradaric on 04/06/17.
 */

/**
 * The customer class is used as a base for business and and private customers
 */
public class Customer {

    private String address;
    private PaymentType paymentType;
    private CustomerType customerType;
    private String remoteId;

    private ArrayList<Subscription> activeSubs;

    /**
     * Constructor of Customer
     * @param type
     */

    protected Customer(CustomerType type) {
        setCustomerType(type);
    }

    /**
     * Getter of Address
     * @return
     */

    public String getAddress() {
        return address;
    }

    /**
     * Setter of Address
     * @param address
     * @throws IllegalArgumentException
     */

    public void setAddress(String address) throws IllegalArgumentException {
        if (address != null && !address.isEmpty()) {
            this.address = address;
        }
        else {
            throw new IllegalArgumentException("Invalid address");
        }
    }

    public void addSubscription(Subscription newSub){
        activeSubs.add(newSub);
    }

    public void cancelSubscription(Subscription subToCancel){
        activeSubs.remove(subToCancel);
    }

    /**
     * Getter of PaymentType
     * @return
     */

    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Setter PaymentType
     * @param paymentType
     */

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Getter for CustomerType Object
     * @return
     */

    public CustomerType getCustomerType() {
        return customerType;
    }

    /**
     * Setter for CustomerType
     * @param customerType
     */

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    /**
     * Getter for remoteId
     * @return
     */

    public String getRemoteId() {
        return remoteId;
    }

    /**
     * Setter for RemoteId
     * @param remoteId
     */

    public void setRemoteId(String remoteId) {
        if (remoteId != null && !remoteId.isEmpty()) {
            this.remoteId = remoteId;
        }
    }

}
