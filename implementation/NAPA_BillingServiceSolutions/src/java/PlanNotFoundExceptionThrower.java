import exceptions.PlanNotFoundException;

/**
 * Created by Andreas on 29.05.2017.
 */
public class PlanNotFoundExceptionThrower {


    public PlanNotFoundExceptionThrower(){

    }

    public void doSomethingThatCanThrowAnException(Boolean withCustomMessage) throws PlanNotFoundException{

        //doing stuff



        if(withCustomMessage)
        {
            //ooooh nooooo! there was a problem
            // ima throw an exception

            PlanNotFoundException p = new PlanNotFoundException();
            p.setMessage("this isn´t so bad");

            throw p;
        }
        else
        {
            throw new PlanNotFoundException();
        }


    }


}
