package com.example.lab6.controller;

import com.example.lab6.model.Product;
import com.example.lab6.model.User;
import com.example.lab6.repository.ProductRepository;
import com.example.lab6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping(path="/lab6")
public class LabController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path="/user")
    public @ResponseBody
    String addUser (@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "User Saved";
    }

    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/product")
    public @ResponseBody
    String addProduct (@RequestParam Double price, @RequestParam String name) {
        Product n = new Product();
        n.setPrice(price);
        n.setName(name);
        productRepository.save(n);
        return "Product Saved";
    }

    @GetMapping(path="/products")
    public @ResponseBody Iterable<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @GetMapping(path="/user-valid") //
    public String addUserValid (@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formUser";
        }
        return "result";
    }

    @GetMapping(path="/product-valid") //
    public String addProductValid (@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formProduct";
        }
        return "result";
    }
}
