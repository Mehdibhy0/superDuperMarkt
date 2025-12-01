import org.example.domain.model.Joghurt;
import org.example.repository.InMemoryProductRepository;
import org.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class JoghurtUpdaterTest {
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
    void testJoghurtDailyUpdate() {
        Joghurt joghurt= new Joghurt("Strawberry Joghurt",7,15.0,today.plusDays(7));
        productRepository.addProduct(joghurt);

        productService.updateAllProducts(today);

        assertEquals(6, joghurt.getQuality());
        assertFalse(joghurt.isMustBeRemoved());
        assertEquals(15.6,joghurt.getCurrentPrice());
    }

    @Test
    void testJoghurtExpiration() {
        Joghurt expiredJoghurt= new Joghurt("Expired Joghurt",3,15.0,today.minusDays(1));
        productRepository.addProduct(expiredJoghurt);

        productService.updateAllProducts(today);

        assertTrue(expiredJoghurt.isMustBeRemoved());
        assertEquals(2,expiredJoghurt.getQuality());
    }


    @Test
    void testJoghurtQualityZeroTriggersRemoval() {
        Joghurt joghurt= new Joghurt("Joghurt",1,15.0,today.plusDays(7));
        productRepository.addProduct(joghurt);

        productService.updateAllProducts(today);

        assertEquals(0,joghurt.getQuality());
        assertTrue(joghurt.isMustBeRemoved());
    }

    @Test
    void testJoghurtPriceCutInHalfOnFifthDay() {
        Joghurt joghurt= new Joghurt("Joghurt",7,15.0,today.plusDays(7));
        productRepository.addProduct(joghurt);
        for(int i=0;i<5;i++){
            productService.updateAllProducts(today);
        }
        assertEquals(7.5,joghurt.getCurrentPrice());
    }
}
