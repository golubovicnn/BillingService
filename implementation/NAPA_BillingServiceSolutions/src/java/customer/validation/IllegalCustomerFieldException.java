package customer.validation;

/**
 * Created by aldinbradaric on 04/06/17.
 */
public class IllegalCustomerFieldException extends Exception {
    private int numErrors = 0;
    CustomerInputFields[] invalidFields;

    public IllegalCustomerFieldException(CustomerInputFields[] invalidFields, int numErrors) {
        this.numErrors = numErrors;
        this.invalidFields = invalidFields;
    }

    public int getNumErrors() {
        return numErrors;
    }

    public CustomerInputFields[] getInvalidFields() {
        return invalidFields;
    }
}
