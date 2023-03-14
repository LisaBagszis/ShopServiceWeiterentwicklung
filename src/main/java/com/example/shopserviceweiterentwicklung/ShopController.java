package com.example.shopserviceweiterentwicklung;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/controller")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
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

    @GetMapping("/orders")
    public List<Order> getOrders(){
        return shopService.listOrders();
    }

    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable String id) {
       Order order = shopService.getOrder(id);
        System.out.println(order);
       return order;
    }

    @PostMapping("/orders")
    public List<Order> getOrderById(@RequestBody int[] ids) {
        List<Order> orders = new ArrayList<>();

        for(int id : ids) {
            orders.add(shopService.getOrder(""+ id));
        }
        return orders;
    }


}
