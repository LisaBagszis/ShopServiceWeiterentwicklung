import com.example.shopserviceweiterentwicklung.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class ShopServiceTest {

    OrderRepo orderRepo = mock(OrderRepo.class);
    ProductRepo productRepo = mock(ProductRepo.class);
    IdService idService = mock(IdService.class);
    ShopService shopService = new ShopService(productRepo, orderRepo, idService);

    @Test
    void addOrder() {
        //GIVEN

        List<String> productIds = List.of("1");

        when(productRepo.get("1"))
                .thenReturn(new Product("1", "Banane"));
        when(orderRepo.add(new Order("randomId", List.of(new Product("1", "Banane")))))
                .thenReturn(new Order("randomId", List.of(new Product("1", "Banane"))));
        when(idService.generateId())
                .thenReturn("randomId");

        //WHEN
        Order actual = shopService.addOrder(productIds);

        //THEN
        Order expected = new Order("randomId", List.of(new Product("1", "Banane")));

        verify(productRepo).get("1");
        verify(orderRepo).add(new Order("randomId", List.of(new Product("1", "Banane"))));
        verify(idService).generateId();
        assertEquals(expected, actual);
    }

    @Test
    void addOrder_whenInvalidId_thenThrowException() {
        //GIVEN
        when(productRepo.get("3")).thenReturn(null);

        List<String> productIds = List.of("3");
        //WHEN
        try {
            shopService.addOrder(productIds);

            //THEN
            fail();

        } catch (NoSuchElementException e) {

        verify(productRepo).get("3");
        }
    }

    @Test
    void listProducts(){
        //GIVEN
        Product p1 = new Product("1", "Banane");
        Product p2 = new Product("2", "Apfel");
        when(productRepo.list()).thenReturn(new ArrayList<Product>(List.of(p1, p2)));

        //WHEN
        List <Product> actual = shopService.listProducts();
        List <Product> expected = new ArrayList<Product>(List.of(p1, p2));

        //THEN
        verify(productRepo).list();
        assertEquals(expected, actual);

    }

    @Test
    void getProductById () {
        //GIVEN
        String id = "6";
        when(productRepo.get(id))
                .thenReturn(new Product("1", "Banane"));
        //WHEN
        Product actual = shopService.getProduct(id);
        Product expected = (new Product("1", "Banane"));

        //THEN
        verify(productRepo).get(id);
        assertEquals(expected, actual);

    }

    @Test
    void listOrders () {
        //GIVEN
        Order o1 = new Order("1", List.of(new Product("1", "Apfel"), new Product("2", "Banane")));
        Order o2 = new Order("2", List.of(new Product("1", "Nutella"), new Product("2", "Banane")));
        when(orderRepo.list())
                .thenReturn(new ArrayList<Order>(List.of(o1, o2)));

        //WHEN
        List <Order> actual = shopService.listOrders();
        List <Order> expected = new ArrayList<Order>(List.of(o1, o2));

        //THEN
        verify(orderRepo).list();
        assertEquals(expected, actual);
    }

    @Test
    void getOrderById () {
        //GIVEN
        String id = "5";
        when(orderRepo.get(id))
                .thenReturn(new Order("1", List.of(new Product("1", "Apfel"), new Product("2", "Banane"))));
        //WHEN
        Order actual = shopService.getOrder(id);
        Order expected = (new Order("1", List.of(new Product("1", "Apfel"), new Product("2", "Banane"))));


        //THEN
        verify(orderRepo).get(id);
        assertEquals(expected, actual);

    }
}