package com.example.shopserviceweiterentwicklung;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor //Konstruktor mit allen final Werten. AllArgsConstructor ohne final Werte.

public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final IdService idService;


    public List<Product> listProducts() {
        return productRepo.list();

    }

    public Product getProduct(String id) {
        return productRepo.get(id);

    }

    public List<Order> listOrders() {
        return orderRepo.list();
    }

    public Order getOrder(String id) {
        Order order = orderRepo.get(id);
        if (order == null) {
            throw new NoSuchElementException("Order with id " + id + " not found");
        }
        return order;
    }

    public Order addOrder(List<String> productIds) {
       List<Product> allProducts = new ArrayList<>();

       for (String productId: productIds){
         Product product =  productRepo.get(productId);

           if (product == null) {
               throw new NoSuchElementException("com.example.shopserviceweiterentwicklung.Product with Id: " + productId + " not found!");
           }
           allProducts.add(product);
       }
       Order order = new Order(idService.generateId(), allProducts);
       orderRepo.add(order);

        return order;
    }

    public Order putOrder(String id, Order order) {
        return orderRepo.putOrder(id, order);
    }

    public void deleteOrder(String id) {
        orderRepo.deleteOrder(id);
    }

}
