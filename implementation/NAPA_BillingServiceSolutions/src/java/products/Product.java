package products;

/**
 * Created by Andreas on 05.06.2017.
 */

/**
 * Base class for product that creates a basic product object
 */
public class Product {
    private String name;
    private String description;
    private int number;

    private Plan[] plans;

    /**
     * Constructor for Product
     * @param name
     * @param description
     * @param number
     */

    public Product(String name, String description, int number){
        this.name = name;
        this.description = description;
        this.number = number;
        plans = new Plan[2];
    }

    /**
     * Getter for Name
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * Getter for Discription
     * @return
     */

    public String getDescription() {return description; }

    /**
     * Getter for Number
     * @return
     */

    public int getNumber() {
        return number;
    }

    /**
     * Add Plans for each Product
     * @param plan
     */

    public void addPlan(Plan plan){
        if(plans[0] == null) {
            plans[0] = plan;
        }
        else {
            plans[1] = plan;
        }
    }

    /**
     * Getter for Array of Plans for each Product
     * @return
     */

    public Plan[] getPlans(){
        return plans;
    }


}
