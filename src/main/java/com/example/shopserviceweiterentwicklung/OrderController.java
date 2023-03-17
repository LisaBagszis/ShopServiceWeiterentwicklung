package com.example.shopserviceweiterentwicklung;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/controller")
public class OrderController {
    private final ShopService shopService;

    public OrderController(ShopService shopService) {
        this.shopService = shopService;
    }


    @GetMapping("/orders")
    public List<Order> getOrders(){
        return shopService.listOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable String id) {
       Order order = shopService.getOrder(id);
        System.out.println(order);
       return order;
    }

    @PostMapping("orders")
    public List<Order> getOrderById(@RequestBody int[] ids) {
        List<Order> orders = new ArrayList<>();

        for(int id : ids) {
            orders.add(shopService.getOrder(""+ id));
        }
        return orders;
    }


    @DeleteMapping("orders/{id}")
    public ResponseEntity deleteOrder(@PathVariable String id) {
        shopService.deleteOrder(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/put/{id}")
    public Order putOrder (@RequestBody Order order, @PathVariable String id) {
        return shopService.putOrder(id, order);
    }

}
