package customer.validation;

/**
 * Created by aldinbradaric on 04/06/17.
 */
public class Validator {

    /**
     * Validator checks all Parameters of validation, if not, than throws a IllegalCustomerFieldException
     * @param firstName
     * @param lastName
     * @throws IllegalCustomerFieldException
     */

    public static void validatePrivatCustomer(String firstName, String lastName) throws IllegalCustomerFieldException {
        CustomerInputFields[] invalidFields = new CustomerInputFields[2];
        boolean foundException = false;
        int numErrors = 0;

        if (firstName == null || firstName.isEmpty()) {
            foundException = true;
            invalidFields[numErrors++] = CustomerInputFields.FIRSTNAME;
        }

        if (lastName == null || lastName.isEmpty()) {
            foundException = true;
            invalidFields[numErrors++] = CustomerInputFields.LASTNAME;
        }

        if (foundException) {
            throw new IllegalCustomerFieldException(invalidFields, numErrors);
        }
    }

    /**
     * Validator checks all Parameters of validation, if not, than throws a IllegalCustomerFieldException
     * @param organization
     * @throws IllegalCustomerFieldException
     */

    public static void validateBusinessCustomer(String organization) throws IllegalCustomerFieldException {
        CustomerInputFields[] invalidFields = new CustomerInputFields[2];
        boolean foundException = false;
        int numErrors = 0;

        if (organization == null || organization.isEmpty()) {
            foundException = true;
            invalidFields[numErrors++] = CustomerInputFields.ORGANIZATION;
        }

        if (foundException) {
            throw new IllegalCustomerFieldException(invalidFields, numErrors);
        }
    }
}
