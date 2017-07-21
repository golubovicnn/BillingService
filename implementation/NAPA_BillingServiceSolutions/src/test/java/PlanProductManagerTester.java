import org.junit.Test;
import products.Plan;
import products.PlanProductManager;
import products.Product;

import java.util.ArrayList;

/**
 * Created by Andreas on 18.06.2017.
 */
public class PlanProductManagerTester {

    @Test
    public void testPlans(){
        ArrayList<Plan> plans = PlanProductManager.getInstance().getPlans();

        for(Plan plan: plans){
            plan.getName();
            plan.getMonthlyRate();
            plan.getDescription();
        }
    }

    @Test
    public void testProducts(){
        ArrayList<Product> products = PlanProductManager.getInstance().getProducts();

        for(Product product:products){
            product.getNumber();
            product.getName();
            Plan[] plans = product.getPlans();
            plans[0].getName();
            plans[1].getName();
            product.getDescription();
        }

    }
}
