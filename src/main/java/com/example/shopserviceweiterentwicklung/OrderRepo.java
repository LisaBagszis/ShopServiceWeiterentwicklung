package com.example.shopserviceweiterentwicklung;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class OrderRepo {
    private Map<String, Order> orders = new HashMap<>();

    public OrderRepo () {
          orders.put("1", new Order("1", List.of(new Product("1", "Apfel"), new Product("2", "Banane"))));
//        orders.put("2", new Order("2", List.of(new Product("1", "Nutella"), new Product("2", "Banane"))));
//        orders.put("3", new Order("3", List.of(new Product("1", "Brötchen"), new Product("2", "Banane"))));

    }

    public OrderRepo(Map<String, Order> orders) {
        this.orders = orders;
    }

    public List<Order> list() {
        return new ArrayList<>(orders.values());
    }

    public Order get(String id) {
        return orders.get(id);
    }

    public Order add(Order order) {
        orders.put(order.getId(), order);
        return order;

    }
    public void deleteOrder(String id) {
        orders.remove(id);
    }

    public Order putOrder (String id, Order order) {
        return orders.put(id, order);
    }
}
