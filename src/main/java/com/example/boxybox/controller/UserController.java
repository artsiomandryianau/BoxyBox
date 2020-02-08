package com.example.boxybox.controller;

import com.example.boxybox.domain.Role;
import com.example.boxybox.domain.User;
import com.example.boxybox.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Artsiom Andryianau
 *
 */
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    /**
     * autowired param user repo
     */
    @Autowired
    private UserRepo userRepo;


    /**
     *
     * @param model - model param
     * @return userList template
     */
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());

        return "userList";
    }


    /**
     *
     * @param user - user edition param
     * @param model - model param
     * @return userEdit template
     */
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }


    /**
     *
     * @param id - id param of delivery
     * @param model - model param
     * @return user template
     */
    @Transactional
    @PostMapping("/delete1/{id}")
    public String deleteUser(@PathVariable("id") Long  id,
                                 Map<String, Object> model) {

        userRepo.deleteById(id);

        return "redirect:/userList";
    }


    /**
     *
     * @param username - new username
     * @param form - form param
     * @param user - user object
     * @return user template
     */
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/user";
    }
}