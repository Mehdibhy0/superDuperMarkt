import org.example.model.Cheese;
import org.example.model.Wine;
import org.example.utils.ProductUpdateHelper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductUpdateHelperTest {
    private final ProductUpdateHelper helper = new ProductUpdateHelper();

    @Test
    void testCheeseQualityDecreaseDaily() {
        Cheese cheese = new Cheese("Gouda", 40, 10.0, LocalDate.now().plusDays(10));
        LocalDate today = LocalDate.now();

        helper.updateCheese(cheese, today);
        assertEquals(39, cheese.getQuality());
    }

    @Test
    void testCheeseMarkedForRemovalWhenQualityBelowThirtyDays() {
        Cheese cheese = new Cheese("Gouda", 30, 10.0, LocalDate.now().plusDays(10));
        LocalDate today = LocalDate.now();

        helper.updateCheese(cheese, today);
        assertTrue(cheese.isMustBeRemoved());
    }

    @Test
    void testCheeseMarkedForRemovalWhenExpired() {
        Cheese cheese = new Cheese("Gouda", 35, 10.0, LocalDate.now());
        LocalDate today = LocalDate.now();

        helper.updateCheese(cheese, today);
        assertTrue(cheese.isMustBeRemoved());
    }

    @Test
    void testWineQualityShouldIncreaseEveryTenDays() {
        Wine wine = new Wine("Wine", 20, 30.0, LocalDate.now().minusDays(10));
        LocalDate today = LocalDate.now();

        helper.updateWine(wine, today);
        assertEquals(21, wine.getQuality());

        helper.updateWine(wine, today.plusDays(10));
        assertEquals(22, wine.getQuality());
    }

    @Test
    void testWineQualityShouldNotIncreaseBeforeTenDays() {
        Wine wine = new Wine("Wine", 20, 30.0, LocalDate.now().minusDays(9));
        LocalDate today = LocalDate.now();

        helper.updateWine(wine, today);
        assertEquals(20, wine.getQuality());
    }

    @Test
    void testWineQualityShouldNotIncreasePastFifty() {
        Wine wine = new Wine("Wine", 50, 30.0, LocalDate.now().minusDays(10));
        LocalDate today = LocalDate.now();

        helper.updateWine(wine, today);
        assertEquals(50, wine.getQuality());
    }
}
