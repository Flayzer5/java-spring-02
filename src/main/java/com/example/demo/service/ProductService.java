package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() { return productRepository.findAll(); }
    public Optional<Product> getProductById(Long id) { return productRepository.findById(id); }
    public Product createProduct(Product product) { return productRepository.save(product); }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(p -> {
            p.setName(updatedProduct.getName());
            p.setPrice(updatedProduct.getPrice());
            return productRepository.save(p);
        }).orElse(null);
    }

    public void deleteProduct(Long id) { productRepository.deleteById(id); }
}
