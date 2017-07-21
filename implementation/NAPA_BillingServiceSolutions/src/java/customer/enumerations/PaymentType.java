package customer.enumerations;

/**
 * Created by aldinbradaric on 04/06/17.
 */
public enum PaymentType { TRANSFER, DIRECT_DEBIT, CASH, PAYPAL, ADVANCED_PAYMENT, CREDITCARD;

    @Override
    public String toString(){
        return name().toLowerCase();
    }

    public int getValue() {
        return ordinal() + 1;
    }

    public static PaymentType getPaymentType(int type) {
        switch(type) {
            case 1:
                return PaymentType.TRANSFER;
            case 2:
                return PaymentType.DIRECT_DEBIT;
            case 3:
                return PaymentType.CASH;
            case 4:
                return PaymentType.PAYPAL;
            case 5:
                return PaymentType.ADVANCED_PAYMENT;
            case 6:
                return PaymentType.CREDITCARD;
            default:
                return PaymentType.CASH;
        }
    }
}
