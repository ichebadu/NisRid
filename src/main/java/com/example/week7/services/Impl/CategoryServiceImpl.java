package com.example.week7.services.Impl;

import com.example.week7.Repo.CategoryRepository;
import com.example.week7.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    public void removeCategoryById(Long id){
        categoryRepository.deleteById(id);
    }
    public Optional<Category> updateCategoryById(Long id){
        return categoryRepository.findById(id);
    }
}
