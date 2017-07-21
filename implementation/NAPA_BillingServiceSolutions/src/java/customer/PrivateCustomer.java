package customer;

import customer.enumerations.CustomerType;
import customer.enumerations.PaymentType;
import customer.enumerations.SalutationType;

/**
 * Created by aldinbradaric on 04/06/17.
 */

/**
 * This class extends Customer and adds private specific variables
 */
public class PrivateCustomer extends Customer {
    private String firstName = "";
    private String lastName = "";
    private String title = "";
    private SalutationType salutation;

    /**
     * FrivateCustomer inherit from CustomerTyp
     * @param type
     */

    protected PrivateCustomer(CustomerType type) {
        super(type);
    }

    /**
     * Constructor of PrivatCustomer
     * @param type
     * @param firstName
     * @param lastName
     * @param title
     * @param salutation
     * @param address
     * @param paymentType
     * @param remoteId
     */

    public PrivateCustomer(CustomerType type, String firstName, String lastName, String title, SalutationType salutation, String address, PaymentType paymentType, String remoteId){
        super(type);
        setFirstName(firstName);
        setLastName(lastName);
        setTitle(title);
        setSalutation(salutation);
        setAddress(address);
        setPaymentType(paymentType);
        setRemoteId(remoteId);
    }

    /**
     * Getter of FirstName
     * @return
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter of FirstName
     * @param firstName
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter of LastName
     * @return
     */

    public String getLastName() {
        return lastName;
    }

    /**
     * Setter of LastName
     * @param lastName
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter of Title
     * @return
     */

    public String getTitle() {
        return title;
    }

    /**
     * Setter of Title
     * @param title
     */

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        }
    }

    /**
     * Getter of SalutationTyp Object
     * @return
     */

    public SalutationType getSalutation() {
        return salutation;
    }

    /**
     * Setter of SalutationTyp Object
     * @param salutation
     */

    public void setSalutation(SalutationType salutation) {
        this.salutation = salutation;
    }
}
