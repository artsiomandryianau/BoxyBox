package com.example.boxybox.controller;

import com.example.boxybox.domain.Role;
import com.example.boxybox.domain.User;
import com.example.boxybox.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

/**
 *
 * @author Artsiom Andryianau
 *
 */
@Controller
public class RegistrationController {

    /**
     * autowired fiel user repo
     */
    @Autowired
    private UserRepo userRepo;


    /**
     *
     * @return registration template
     */
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    /**
     *
     * @param user - adding user logic param
     * @param model - model param
     * @return login template
     */
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}