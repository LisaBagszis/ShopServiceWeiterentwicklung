package com.example.shopserviceweiterentwicklung;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Repository
public class ProductRepo {

    protected HashMap<String, Product> products;

    public ProductRepo () {
        products = new HashMap<>();
        products.put("1", new Product("1", "Banane"));
        products.put("2", new Product("2", "Apfel"));
        products.put("3", new Product("3", "Kokosnuss"));
    }

    public ProductRepo(HashMap<String, Product> products) {
        this.products = products;
    }

    public List<Product> list() {
        List<Product> productList = new ArrayList<>(products.values());
        return productList;
    }


    public Product get(String id){
        return products.get(id);
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductRepo that = (ProductRepo) o;

        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return products != null ? products.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "com.example.shopserviceweiterentwicklung.ProductRepo{" +
                "products=" + products +
                '}';
    }
}
