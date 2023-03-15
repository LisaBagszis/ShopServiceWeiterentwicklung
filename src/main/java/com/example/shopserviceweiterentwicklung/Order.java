package com.example.shopserviceweiterentwicklung;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private List<Product> products;


}
