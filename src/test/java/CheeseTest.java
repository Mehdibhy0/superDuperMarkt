import org.example.domain.model.Cheese;
import org.example.domain.model.Wine;
import org.example.repository.InMemoryProductRepository;
import org.example.service.ProductService;
import org.example.domain.updater.CheeseUpdater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheeseTest {
    private InMemoryProductRepository productRepository;
    private ProductService productService;
    private LocalDate today;

    @BeforeEach
    void setup() {
        productRepository = new InMemoryProductRepository();
        productService = new ProductService(productRepository);
        today = LocalDate.now();
    }

    @Test
    void testUpdateCheese() {
        Cheese gouda = new Cheese("Gouda", 40, 10.0, today.plusDays(5));

        productRepository.addProduct(gouda);

        for (int i = 0; i < 5; i++) {
            productService.updateAllProducts(today.plusDays(i));
        }
        assertEquals(35, gouda.getQuality());
        assertFalse(gouda.isMustBeRemoved());
        assertEquals(10 + 35 * 0.10, gouda.getCurrentPrice());
    }


    @Test
    void testCheeseRemovalWhenExpiredOrLowQuality() {
        Cheese expired = new Cheese("expired", 40, 10.0, today.minusDays(1));
        Cheese lowQuality = new Cheese("lowQuality", 30, 10.0, today.plusDays(5));

        productRepository.addProduct(expired);
        productRepository.addProduct(lowQuality);

        productService.updateAllProducts(today);

        assertTrue(expired.isMustBeRemoved());
        assertTrue(lowQuality.isMustBeRemoved());
    }

    @Test
    void testMultipleDaysUpdate() {
        Cheese gouda = new Cheese("Gouda", 40, 10.0, today.plusDays(5));

        productRepository.addProduct(gouda);

        for (int i = 0; i < 10; i++) {
            productService.updateAllProducts(today.plusDays(i));
        }
        assertEquals(30, gouda.getQuality());
    }

    @Test
    void testShouldThrowIllegalArgumentsExceptionWrongProduct(){
        CheeseUpdater updater = new CheeseUpdater();
        Wine merlot = new Wine("Merlot", 40, 10.0, today.plusDays(5));
        assertThrows(IllegalArgumentException.class, () -> updater.update(merlot, today));
    }
}