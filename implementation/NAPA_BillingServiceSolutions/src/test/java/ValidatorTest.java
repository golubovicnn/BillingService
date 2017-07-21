import customer.validation.IllegalCustomerFieldException;
import customer.validation.Validator;
import org.junit.Test;


/**
 * Created by Andreas on 04.06.2017.
 */
public class ValidatorTest {

    @Test (expected = IllegalCustomerFieldException.class)
    public void testValidatorThrow() throws IllegalCustomerFieldException {
       Validator.validatePrivatCustomer(null, "Lenz");
       Validator.validatePrivatCustomer(null, "Lenz");
       Validator.validatePrivatCustomer(null, "Lenz");
    }

    @Test
    public void testValidatorCorrect() throws IllegalCustomerFieldException {
        Validator.validatePrivatCustomer("Andi", "Lenz");
        Validator.validatePrivatCustomer("Aldin", "Bradaric");
        Validator.validatePrivatCustomer("Phipsi", "Kuhbauer");
    }
}
