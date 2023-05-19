package com.example.week7.controller;

import com.example.week7.dto.UserDTO;
import com.example.week7.model.Category;
import com.example.week7.dto.ProductDTO;
import com.example.week7.model.Product;
import com.example.week7.services.Impl.CategoryServiceImpl;
import com.example.week7.services.Impl.ProductServiceImpl;
import com.example.week7.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    CategoryServiceImpl categoryServiceImpl;
    @Autowired
    ProductServiceImpl productServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;



//    @GetMapping("/adminLogin")
//    public String loginPage(@ModelAttribute("loginRequest") UserDTO userDTO){
//        UserDTO varifyingLogIn = userServiceImpl.AdminAuthenticate(userDTO);
//        if (varifyingLogIn == userDTO)
//            return "login";
//        return varifyingLogIn != null ? "adminHome" :"register";
//    }

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String getCategory(Model model){
        model.addAttribute("category", new Category());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCart(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    public String postCategoryToCart(@ModelAttribute("category") Category category){
        categoryServiceImpl.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCart(@PathVariable Long id){
        categoryServiceImpl.removeCategoryById(id);
        return "redirect:/admin/category";
    }
    @GetMapping("/admin/categories/update/{id}")
    public String updateCart(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryServiceImpl.updateCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else {
            return "404";
        }
    }
    //product session
    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products", productServiceImpl.getAllProduct());
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String productAddGet(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryServiceImpl.getAllCategory());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
        public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
                                     @RequestParam("productImage") MultipartFile file,
                                     @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryServiceImpl.updateCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }else{
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productServiceImpl.addProduct(product);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productServiceImpl.removeProductById(id);
        return "redirect:/admin/products";
    }
    @GetMapping ("/admin/product/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        Product product = productServiceImpl.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        model.addAttribute("categories", categoryServiceImpl.getAllCategory());
        model.addAttribute("productDTO",productDTO);
        return "productsAdd";
    }
}
