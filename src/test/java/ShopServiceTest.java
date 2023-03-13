import com.example.shopserviceweiterentwicklung.Order;
import com.example.shopserviceweiterentwicklung.Product;
import com.example.shopserviceweiterentwicklung.ShopService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ShopServiceTest {

    @Test
    void addOrder() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productIds);

        //THEN
        Order expected = new Order("", List.of(new Product("1", "Banane")));
        assertEquals(expected, actual);
    }

    @Test
    void addOrder_whenIvalidId_thenThrowException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productIds = List.of("5");
        //WHEN
        try {
            shopService.addOrder(productIds);
            //THEN
            fail();

        } catch (NoSuchElementException e) {


        }
    }
}