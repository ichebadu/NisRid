package com.example.week7.services;

import com.example.week7.dto.ProductDTO;
import com.example.week7.model.Product;


import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();
    void addProduct(Product product);
    void removeProductById(Long id);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProductsByCategoryId(Long id);
}
