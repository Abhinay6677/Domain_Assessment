package com.ecommerce.product.repository;
 
import com.ecommerce.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
 
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategory(String category);
 
    List<Product> findByPriceGreaterThan(Double price);
    List<Product> findByPriceLessThan(Double price);
}