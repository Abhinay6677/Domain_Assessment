package com.ecommerce.order.controller;
 
import com.ecommerce.order.model.Order;
import com.ecommerce.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.Map;
 
@RestController
@RequestMapping("/orders")
public class OrderController {
 
    @Autowired
    private OrderRepository orderRepository;
 
    @Autowired
    private RestTemplate restTemplate;
 
    @SuppressWarnings("rawtypes")
	@PostMapping
    public Order placeOrder(@RequestBody Order order) {

        String productServiceUrl = "http://product-service/products/" + order.getProductId();
        
        Map productDetails = restTemplate.getForObject(productServiceUrl, Map.class);
        
        if (productDetails != null) {
            Double price = (Double) productDetails.get("price");
            order.setTotalAmount(price * order.getQuantity());
            order.setOrderDate(LocalDateTime.now());
            return orderRepository.save(order);
        }
        
        throw new RuntimeException("Product validation failed. Service unavailable or invalid ID.");
    }
}