package com.example.shopserviceweiterentwicklung;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
