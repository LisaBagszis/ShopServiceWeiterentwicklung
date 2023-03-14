package com.example.shopserviceweiterentwicklung;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controller")
public class ProductController {

    private final ShopService shopService;

    public ProductController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return shopService.listProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable String id) {
        return shopService.getProduct(id);
    }

}
