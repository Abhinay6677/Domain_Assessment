package com.ecommerce.order.controller;
 
import com.ecommerce.order.model.Order;
import com.ecommerce.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
	@CircuitBreaker(name = "productServiceCB", fallbackMethod = "productServiceFallback")
    public Order placeOrder(@RequestBody Order order) {

    	String userServiceUrl = "http://user-service/users/" + order.getCustomerId();
    	Map userProfile = restTemplate.getForObject(userServiceUrl, Map.class);
    	
    	if (userProfile == null) {
    		throw new  RuntimeException("Invalid order: Registered User Profile not found");
    	}
    	
        String productServiceUrl = "http://product-service/products/" + order.getProductId();
        Map productDetails = restTemplate.getForObject(productServiceUrl, Map.class);
        
        if (productDetails != null) {
            Double price = (Double) productDetails.get("price");
            order.setTotalAmount(price * order.getQuantity());
            order.setOrderDate(LocalDateTime.now());
            return orderRepository.save(order);
        }
        
        throw new RuntimeException("Product details could not be validated.");
    }
    
    public Order productServiceFallback(Order order, Throwable throwable) {
    	Order fallbackOrder = new Order();
    	fallbackOrder.setCustomerId(order.getCustomerId());
    	fallbackOrder.setProductId(order.getProductId());
    	fallbackOrder.setQuantity(order.getQuantity());
    	fallbackOrder.setTotalAmount(0.0);
    	fallbackOrder.setOrderDate(LocalDateTime.now());
    	
    	System.out.println("CRITICAL: Product Service is DOWN! Circuit Breaker Fallback triggered: " + throwable.getMessage());
    	return fallbackOrder;
    }
}