package com.example.week7.Repo;

import com.example.week7.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    //List<Product> get(Long id);
    List<Product> findAllByCategory_Id(Long id);
}
