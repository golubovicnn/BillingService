package products;

import localization.Resources;

import java.util.ArrayList;

/**
 * Created by Andreas on 18.06.2017.
 */
public class PlanProductManager {

    private ArrayList<Plan> plans;
    private ArrayList<Product> products;

    private static PlanProductManager ourInstance = new PlanProductManager();

    /**
     * Getter of PlanProductManager
     * @return
     */

    public static PlanProductManager getInstance() {
        return ourInstance;
    }


    /**
     * PlanProductManager prepare Products and Plans for making Articels
     */
    private PlanProductManager() {
        plans = new ArrayList<>();
        products = new ArrayList<>();

        final Plan planBasic = new Plan(Resources.getInstance().label("PlanBasicTitle"), Resources.getInstance().label("PlanBasicDesc"), Double.parseDouble(Resources.getInstance().label("PlanBasicRate")));
        final Plan planAdvanced = new Plan(Resources.getInstance().label("PlanAdvancedTitle"), Resources.getInstance().label("PlanAdvancedDesc"), Double.parseDouble(Resources.getInstance().label("PlanAdvancedRate")));

        plans.add(planBasic);
        plans.add(planAdvanced);
        final Product product1 = new Product(Resources.getInstance().label("Prod1Title"), Resources.getInstance().label("Prod1Desc"),1);
        final Product product2 = new Product(Resources.getInstance().label("Prod2Title"), Resources.getInstance().label("Prod2Desc"),2);
        final Product product3 = new Product(Resources.getInstance().label("Prod3Title"), Resources.getInstance().label("Prod3Desc"),3);

        product1.addPlan(plans.get(0));
        product1.addPlan(plans.get(1));

        product2.addPlan(plans.get(0));
        product2.addPlan(plans.get(1));

        product3.addPlan(plans.get(0));
        product3.addPlan(plans.get(1));

        products.add(product1);
        products.add(product2);
        products.add(product3);

    }

    /**
     * Getter for Plan with the Price
     * @param price
     * @return
     */
    public Plan getPlanByPrice(double price){
        for(Plan plan : plans){
            if(plan.getMonthlyRate() == price){
                return plan;
            }
        }

        return null;
    }

    /**
     * Getter for Product with Number
     * @param productNumber
     * @return
     */

    public Product getProductByNumber(int productNumber){
        for(Product product : products){
            if(product.getNumber() == productNumber){
                return product;
            }
        }
        return null;
    }

    /**
     * Getter for Arraylist with Products
     * @return
     */

    public ArrayList<Product> getProducts(){
        return products;
    }

    /**
     * Getter for Arraylist with Plans
     * @return
     */

    public ArrayList<Plan> getPlans() {
        return plans;
    }
}
