package com.example.shopserviceweiterentwicklung;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/controller")
public class ShopController {

    ShopService shopservice = new ShopService();

    @GetMapping("/products")
    public List<Product> getProducts() {
        return shopservice.listProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable String id) {
        return shopservice.getProduct(id);
    }

    @GetMapping("/orders")
    public List<Order> getOrders(){
        return shopservice.listOrders();
    }

    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable String id) {
       Order order = shopservice.getOrder(id);
        System.out.println(order);
       return order;
    }

    @PostMapping("/orders")
    public List<Order> getOrderById(@RequestBody int[] ids) {
        List<Order> orders = new ArrayList<>();

        for(int id : ids) {
            orders.add(shopservice.getOrder(""+ id));
        }
        return orders;
    }


}
