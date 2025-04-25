package praktikum;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;


    @Before
    public void setUp() {
        burger = new Burger();


        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(200f);


        when(ingredient1.getName()).thenReturn("cutlet");
        when(ingredient1.getPrice()).thenReturn(100f);
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);


        when(ingredient2.getName()).thenReturn("hot sauce");
        when(ingredient2.getPrice()).thenReturn(100f);
        when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);
    }


    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }


    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }


    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }


    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);


        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }


    @Test
    public void testGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = 200f * 2 + 100f + 100f;

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String receipt = burger.getReceipt();

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                bun.getName(),
                ingredient1.getType().toString().toLowerCase(), ingredient1.getName(),
                ingredient2.getType().toString().toLowerCase(), ingredient2.getName(),
                bun.getName(),
                burger.getPrice()
        );

        assertEquals(expectedReceipt, receipt);
    }
}
