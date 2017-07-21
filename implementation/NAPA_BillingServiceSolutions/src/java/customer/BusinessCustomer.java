package customer;

import customer.enumerations.CustomerType;
import customer.enumerations.PaymentType;

/**
 * Created by aldinbradaric on 04/06/17.
 */

/**
 * This class extends Customer and adds class specific variables
 */
public class BusinessCustomer extends Customer {
    private String organization;

    /**
     * BusinessCustomer inherit from CustomerTyp
     * @param type
     */

    protected BusinessCustomer(CustomerType type) {
        super(type);
    }

    /**
     * Constructor of Business Customer
     * @param type
     * @param organization
     * @param address
     * @param paymentType
     * @param remoteId
     */

    public BusinessCustomer(CustomerType type, String organization, String address, PaymentType paymentType, String remoteId){
        super(type);
        setOrganization(organization);
        setAddress(address);
        setPaymentType(paymentType);
        setRemoteId(remoteId);
    }

    /**
     * Getter for Organization returns Organization
     * @return
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Setter for Organization
     * @param organization
     */

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
