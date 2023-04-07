package com.example.week7.services.Impl;

import com.example.week7.Repo.ProductRepository;
import com.example.week7.model.Product;
import com.example.week7.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct(){
       return productRepository.findAll();
    }
    @Override
    public void addProduct(Product product){
        productRepository.save(product);
    }
    @Override
    public void removeProductById(Long id){
        productRepository.deleteById(id );
    }
    @Override
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }
    @Override
    public List<Product> getAllProductsByCategoryId(Long id){
        return productRepository.findAllByCategory_Id(id);
    }
}
