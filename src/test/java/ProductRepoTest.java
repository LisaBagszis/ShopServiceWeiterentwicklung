import com.example.shopserviceweiterentwicklung.Product;
import com.example.shopserviceweiterentwicklung.ProductRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductRepoTest {

    @Test
    void list() {
        //GIVEN
        ProductRepo productRepo = new ProductRepo();

        //WHEN
        List<Product> actual = productRepo.list();

        //THEN
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("1", "Banane"));
        expected.add(new Product("2", "Apfel"));
        expected.add(new Product("3", "Kokosnuss"));

        assertEquals(expected, actual);


    }

    @Test
    void get() {
        //GIVEN
        ProductRepo productRepo = new ProductRepo();

        //WHEN
        Product actual = productRepo.get("1");

        //Then
        Product expected  = new Product("1", "Banane");

        assertEquals(expected, actual);
    }
}