package com.example.shopserviceweiterentwicklung;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShopIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ShopService shopService;

    @Test
    void getOrders_shouldReturnListOfOrders() throws Exception {

        Order order = new Order("1", List.of(new Product("1", "Apfel"), new Product("2", "Banane")));
        orderRepo.add(order);

        mockMvc.perform(get("/controller/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                
                                    {
                                    "id": "1",
                                                  "products": [
                                                      {
                                                          "id": "1",
                                                          "name": "Apfel"
                                                      },
                                                      {
                                                          "id": "2",
                                                          "name": "Banane"
                                                      }
                                                      ]
                                    }
                                    ]

                        
                            
                                        """));
    }

    @Test
    void getOrderById_getOrderWhenSameId() throws Exception{
        Order order = new Order("1", List.of(new Product("1", "Apfel"), new Product("2", "Banane")));
        orderRepo.get(order.getId());

        mockMvc.perform(get("/controller/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                
                                
                                    {
                                    "id": "1",
                                                  "products": [
                                                      {
                                                          "id": "1",
                                                          "name": "Apfel"
                                                      },
                                                      {
                                                          "id": "2",
                                                          "name": "Banane"
                                                      }
                                                      ]
                                    }
                                    

                        
                            
                                        """));
    }


    @Test
    void deleteOrder_shouldReturnNoContent() throws Exception {

        String orderId = "1";
        doNothing().when(shopService).deleteOrder(orderId);


        mockMvc.perform(delete("/orders/{id}", orderId))


                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }


    @Test
    @DirtiesContext
    void putOrder_shouldReturnCreatedOrder() throws Exception {

        mockMvc.perform(

                        MockMvcRequestBuilders.put("/put/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        """
                                                {
                                    "id": "1",
                                                  "products": [
                                                      {
                                                          "id": "1",
                                                          "name": "Apfel"
                                                      },
                                                      {
                                                          "id": "2",
                                                          "name": "Banane"
                                                      }
                                                      ]
                                    }
                                                """
                                ))
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().json(
                                """
                                         {
                                    "id": "1",
                                                  "products": [
                                                      {
                                                          "id": "1",
                                                          "name": "Apfel"
                                                      },
                                                      {
                                                          "id": "2",
                                                          "name": "Banane"
                                                      }
                                                      ]
                                    }
                                        """
                        ));
    }


}