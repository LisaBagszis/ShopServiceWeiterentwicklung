package com.example.shopserviceweiterentwicklung;

import java.util.List;

public class Main {
    public static void main(String[] args) {

    ShopService shopService = new ShopService();

    shopService.listProducts();
    shopService.getProduct("2");
    shopService.listOrders();

    shopService.addOrder(List.of("1", "2", "3"));

    shopService.listOrders();


    }
}