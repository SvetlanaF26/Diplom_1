package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class IngredientTest {

    @Mock
    private Database database;

    private final String ingredientName;
    private final IngredientType ingredientType;
    private final float ingredientPrice;

    public IngredientTest(String ingredientName, IngredientType ingredientType, float ingredientPrice) {
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters
    public static Object[][] ingredientParam() {
        return new Object[][] {
                {"hot sauce", IngredientType.SAUCE, 100f},
                {"sour cream", IngredientType.SAUCE, 200f},
                {"chili sauce", IngredientType.SAUCE, 300f},
                {"cutlet", IngredientType.FILLING, 100f},
                {"dinosaur", IngredientType.FILLING, 200f},
                {"sausage", IngredientType.FILLING, 300f}
        };
    }

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIngredientCreation() {

        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

        assertEquals(ingredientType, ingredient.getType());
        assertEquals(ingredientName, ingredient.getName());
        assertEquals(ingredientPrice, ingredient.getPrice(), 0.001);
    }

    @Test
    public void testIngredientTypeEnum() {

        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    public void testAvailableIngredientsFromDatabase() {

        Ingredient ingredientOne = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        Ingredient ingredientTwo = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        when(database.availableIngredients()).thenReturn(Arrays.asList(ingredientOne, ingredientTwo));

        List<Ingredient> ingredients = database.availableIngredients();

        assertEquals(2, ingredients.size());
        assertEquals("hot sauce", ingredients.get(0).getName());
        assertEquals("cutlet", ingredients.get(1).getName());
    }

    @Test
    public void testAvailableBunsFromDatabase() {

        Bun bunOne = new Bun("black bun", 100);
        Bun bunTwo = new Bun("white bun", 200);
        when(database.availableBuns()).thenReturn(Arrays.asList(bunOne, bunTwo));

        List<Bun> buns = database.availableBuns();

        assertEquals(2, buns.size());
        assertEquals("black bun", buns.get(0).getName());
        assertEquals("white bun", buns.get(1).getName());
    }

}
