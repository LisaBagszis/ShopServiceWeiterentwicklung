package com.example.shopserviceweiterentwicklung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepo {
    private Map<String, Order> orders = new HashMap<>();

    public OrderRepo () {
        orders.put("1", new Order("1", List.of(new Product("1", "Apfel"), new Product("2", "Banane"))));
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
}
