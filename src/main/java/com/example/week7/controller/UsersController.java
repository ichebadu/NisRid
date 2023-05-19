package com.example.week7.controller;

import com.example.week7.dto.UserDTO;
import com.example.week7.model.User;
import com.example.week7.services.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class UsersController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new UserDTO());
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerRequest", new UserDTO());
        return "register";
    }

    @GetMapping("/logout")
    public String logout() {
        return "shop";
    }

    @PostMapping("/register")
    public String registerPageView(@ModelAttribute("registerRequest") UserDTO userDTO) {
        UserDTO registeredUser = userServiceImpl.registerUser(userDTO);
        return registeredUser != null ? "checkout" : "login";
    }

    @PostMapping("/login")
    public String loginPage(@ModelAttribute("loginRequest") UserDTO userDTO) {
        UserDTO authenticatedUser = userServiceImpl.Authenticate(userDTO);
        if (authenticatedUser != null) {
            if (userDTO.getEmail().equals("ichebadu@gmail.com")) {
                return "adminHome";
            } else if (userDTO.getEmail().equals(userDTO)) {
                return "shop";
            } else {
                return "register";
            }
        }
        return "register";
    }
}
//    @GetMapping("/cart")
//    public String Cart(){
//        return "cart";
//    }
//    @GetMapping("/cart")
//    public String addToCart(Model model){
//        model.addAttribute("cart",new UserDTO());
//        return "cart";
//    }
//    @GetMapping("/home")
//    public String userList(Model model){
//        List<UserDTO> userList = userServiceImpl.findAllUsers();
//        model.addAttribute("userList",userList);
//        return "user-list";
//    }

//    @GetMapping("/signup")
//    public ModelAndView create(ModelAndView modelAndView){
//        modelAndView.setViewName("signup");
//        modelAndView.addObject("user",new UserDTO());
//        return modelAndView;
//    }
 //   }
