package customer.enumerations;

/**
 * Created by aldinbradaric on 04/06/17.
 */

/**
 * for kategorize user
 */
public enum CustomerType {
    CONSUMER(){

        @Override
        public String toString(){
            return "consumer";
        }
    },
    BUSINESS(){

        @Override
        public String toString(){
            return "business";
        }
    };

    /**
     * select Strategiepatern for User
     * @param string
     * @return
     */
    public static CustomerType getCustomerType(String string) {
      switch(string) {
          case "business":
              return CustomerType.BUSINESS;
          case "consumer":
              return CustomerType.CONSUMER;
          default:
              return CustomerType.CONSUMER;
      }
    }
}
