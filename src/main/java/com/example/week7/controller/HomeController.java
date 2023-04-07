package com.example.week7.controller;


import com.example.week7.dto.UserDTO;
import com.example.week7.services.Impl.CategoryServiceImpl;
import com.example.week7.services.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    CategoryServiceImpl categoryServiceImpl;
    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping ({"/","/home"})
    public String home(Model model){
        return "index";
    }
    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories", categoryServiceImpl.getAllCategory());
        model.addAttribute("products",productServiceImpl.getAllProduct());
        model.addAttribute("user",new UserDTO());
        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public  String shopByCategory(Model model, @PathVariable Long id){
        model.addAttribute("categories", categoryServiceImpl.getAllCategory());
        model.addAttribute("products", productServiceImpl.getAllProductsByCategoryId(id));
        model.addAttribute("user", new UserDTO());
        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public  String viewProduct(Model model, @PathVariable Long id){
        model.addAttribute("product", productServiceImpl.getProductById(id).get());
        return "viewProduct";
    }
}
