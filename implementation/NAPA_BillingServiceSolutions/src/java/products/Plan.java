package products;

/**
 * Created by Andreas on 05.06.2017.
 */
public class Plan {

    protected String name;
    protected String description;
    protected double monthlyRate;


    /**
     * Constructor
     * @param name
     * @param description
     * @param monthlyRate
     */

    public Plan(String name, String description, double monthlyRate){
        this.name = name;
        this.description = description;
        this.monthlyRate = monthlyRate;
    }

    /**
     * Getter of Name
     * @return
     */


    public String getName() {
        return name;
    }

    /**
     * Getter of Description
     * @return
     */

    public String getDescription() {
        return description;
    }

    /**
     * Getter of Monthly Rate
     * @return
     */

    public double getMonthlyRate() {
        return monthlyRate;
    }


}
