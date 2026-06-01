package com.ecommerce.product.controller;
 
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.List;
 
@RestController
@RequestMapping("/products")
public class ProductController {
 
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
 
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }
 
    @GetMapping("/sorted")
    public List<Product> getAllProductsSorted(@RequestParam String property, @RequestParam String dir) {
        Sort sort = dir.equalsIgnoreCase("desc") ? Sort.by(property).descending() : Sort.by(property).ascending();
        return productRepository.findAll(sort);
    }
 
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productRepository.findByCategory(category);
    }
 
    @GetMapping("/price/greater/{price}")
    public List<Product> getPriceGreaterThan(@PathVariable Double price) {
        return productRepository.findByPriceGreaterThan(price);
    }
}