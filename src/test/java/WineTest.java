import org.example.domain.model.Cheese;
import org.example.domain.model.Wine;
import org.example.repository.InMemoryProductRepository;
import org.example.service.ProductService;
import org.example.domain.updater.WineUpdater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WineTest {
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
    void testUpdateWine() {
        Wine merlot = new Wine("Merlot", 20, 30.0, today.minusDays(20));
        Wine chardonnay = new Wine("Chardonnay", 10, 25.0, today.minusDays(35));

        productRepository.addProduct(merlot);
        productRepository.addProduct(chardonnay);

        productService.updateAllProducts(today);

        assertEquals(22, merlot.getQuality());
        assertEquals(13, chardonnay.getQuality());
        assertFalse(merlot.isMustBeRemoved());
        assertEquals(30.0, merlot.getCurrentPrice());
    }


    @Test
    void testMultipleDaysUpdate() {
        Wine merlot = new Wine("Merlot", 20, 30.0, today.minusDays(5));

        productRepository.addProduct(merlot);

        for (int i = 0; i < 10; i++) {
            productService.updateAllProducts(today.plusDays(i));
        }
        assertEquals(21, merlot.getQuality());
    }

    @Test
    void testWineMaxQualityCap() {
        Wine merlot = new Wine("Merlot", 50, 30.0, today.minusDays(10));

        productRepository.addProduct(merlot);

        productService.updateAllProducts(today);

        assertEquals(50, merlot.getQuality());
    }

    @Test
    void testShouldThrowIllegalArgumentsExceptionWrongProduct(){
        WineUpdater updater = new WineUpdater();
        Cheese gouda = new Cheese("Gouda", 40, 10.0, today.plusDays(5));
        assertThrows(IllegalArgumentException.class, () -> updater.update(gouda, today));
    }
}
