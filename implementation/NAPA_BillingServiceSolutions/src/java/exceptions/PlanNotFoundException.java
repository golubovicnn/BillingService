package exceptions;

/**
 * Created by Andreas on 29.05.2017.
 */
public class PlanNotFoundException extends Exception{

    String myMessage =  "The plan was not found! duh";

    /**
     * Getter of Exception-Message
     * @return
     */

    @Override
    public String getMessage(){
        return myMessage;
    }

    /**
     * Setter of Exception-Message
     * @param newMessage
     */

    public void setMessage(String newMessage){
        myMessage = newMessage;
    }

}
