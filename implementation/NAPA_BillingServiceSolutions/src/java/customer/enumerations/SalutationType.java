package customer.enumerations;

/**
 * Created by aldinbradaric on 04/06/17.
 */

/**
 * Based on the salutation option the user chooses, the using class picks the specific enum
 */
public enum SalutationType { MR, MRS, FAMILY, EMPTY;


    /**
     * to String Name
     * @return
     */
    @Override
    public String toString(){
        return name().toLowerCase();
    }

    /**
     * Select SalutationTyp
     * @param string
     * @return
     */

    public static SalutationType getSalutationType(String string) {
        switch(string) {
            case "mr":
                return SalutationType.MR;
            case "mrs":
                return SalutationType.MRS;
            case "family":
                return SalutationType.FAMILY;
            case "empty":
                return SalutationType.EMPTY;
            default:
                return SalutationType.EMPTY;
        }
    }
}
