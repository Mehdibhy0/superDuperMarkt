import org.example.model.Cheese;
import org.example.model.Wine;
import org.example.repository.InMemoryProductRepository;
import org.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductServiceTest {
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
    void testUpdateAllProducts_cheeseAndWine(){
        Cheese gouda = new Cheese("Gouda",40, 10.0,today.plusDays(5));
        Wine merlot = new Wine("Merlot",20,30.0,today.minusDays(10));

        productRepository.addProduct(gouda);
        productRepository.addProduct(merlot);

        productService.updateAllProducts(today);

        assertEquals(39, gouda.getQuality());
        assertFalse(gouda.isMustBeRemoved());
        assertEquals(10+39*0.10, gouda.getCurrentPrice());

        assertEquals(21, merlot.getQuality());
        assertFalse(merlot.isMustBeRemoved());
        assertEquals(30.0, merlot.getCurrentPrice());
    }


    @Test
    void testCheeseRemovalWhenExpiredOrLowQuality(){
        Cheese expired = new Cheese("expired",40, 10.0,today.minusDays(1));
        Cheese lowQuality = new Cheese("lowQuality",30, 10.0,today.plusDays(5));

        productRepository.addProduct(expired);
        productRepository.addProduct(lowQuality);

        productService.updateAllProducts(today);

        assertTrue(expired.isMustBeRemoved());
        assertTrue(lowQuality.isMustBeRemoved());
    }

    @Test
    void testMultipleDaysUpdate(){
        Cheese gouda = new Cheese("Gouda",40, 10.0,today.plusDays(5));
        Wine merlot = new Wine("Merlot",20,30.0,today.minusDays(5));

        productRepository.addProduct(merlot);
        productRepository.addProduct(gouda);

        for(int i=0; i<10; i++){
            productService.updateAllProducts(today.plusDays(i));
        }
        assertEquals(30, gouda.getQuality());
        assertEquals(21, merlot.getQuality());
    }
}
