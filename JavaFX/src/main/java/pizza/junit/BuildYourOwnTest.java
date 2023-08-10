package pizza.junit;

import org.junit.Test;
import pizza.*;

import static org.junit.Assert.*;

/**
 * Class used to test output of the method price()
 * from BuildYourOwn
 * @author Sriyaank Vadlamani, Paul Manayath
 */

//**This was tested using JUNIT 4**
public class BuildYourOwnTest {

    /**
     * Checks price of pizza when no toppings are added
     * Base prices are the same between chicago style and ny style
     */
    @Test
    public void check_price_no_toppings() {
        PizzaFactory pf = new ChicagoPizza();
        Pizza p = pf.createBuildYourOwn();
        //case 1 -> Chicago Style Build Your Own - Small
        p.setSize(String.valueOf(Size.SMALL));
        assertEquals(8.99, p.price(), 0.0);

        //case 2 -> Chicago Style Build Your Own - Medium
        p.setSize(String.valueOf(Size.MEDIUM));
        assertEquals(10.99, p.price(), 0.0);

        //case 2 -> Chicago Style Build Your Own - Medium
        p.setSize(String.valueOf(Size.LARGE));
        assertEquals(12.99, p.price(), 0.0);
    }

    /**
     * Checks price of pizza when toppings are added
     * Base prices are the same between chicago style and ny style
     */
    @Test
    public void check_price_with_toppings() {
        PizzaFactory pf = new NYPizza();
        Pizza p = pf.createBuildYourOwn();

        //case 1 -> Small -> 3 Toppings
        // 8.99 + (1.59 * 3)
        p.setSize(String.valueOf(Size.SMALL));
        p.add(new Topping("Sausage")); p.add(new Topping("Olive")); p.add(new Topping("BBQ Chicken"));
        assertEquals(13.76, p.price(), 0.0);

        // case 2 -> Medium -> 7 Toppings
        // 10.99 + (1.59 * 7)
        p.setSize(String.valueOf(Size.MEDIUM));
        p.add(new Topping("Provolone")); p.add(new Topping("Cheddar")); p.add(new Topping("Beef"));
        p.add(new Topping("Jalapeno"));
        assertEquals(22.12, p.price(), 0.0);

        // case 3 -> Large -> 5 toppings
        // 12.99 + (1.59 * 5)
        p.setSize(String.valueOf(Size.LARGE));
        p.remove(new Topping("Jalapeno")); p.remove(new Topping("Beef"));
        assertEquals(20.94, p.price(), 0.0);

        // case 4 -> Large -> 8 toppings
        // 12.99 + (1.59 * 7) -> Should not add any more toppings
        // therefore the cost of the 8th topping will not be added
        p.add(new Topping("Jalapeno")); p.add(new Topping("Beef")); p.add(new Topping("Green Pepper"));
        assertEquals(24.12, p.price(), 0.0);
    }
}