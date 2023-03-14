package com.example.shopserviceweiterentwicklung;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
@Service
public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

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
        return orderRepo.get(id);
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
       Order order = new Order(UUID.randomUUID().toString(), allProducts);
       orderRepo.add(order);

        return order;
    }
}
