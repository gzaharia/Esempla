package com.esempla.blog.controller;

import com.esempla.blog.domain.Roles;
import com.esempla.blog.domain.RolesType;
import com.esempla.blog.domain.Users;
import com.esempla.blog.repository.CategoryRepository;
import com.esempla.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Controller
public class LoginCard {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/loginPage")
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout, Principal principal) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided.");
        }

        if (logout != null) {
            model.addObject("logout", "Logged out successfully.");
        }
        model.addObject("userDetails", principal);
        model.setViewName("loginPage");
        return model;
    }


    @GetMapping("/logoutPage")
    public ModelAndView logoutPage(Principal principal) {
        ModelAndView model = new ModelAndView();

        model.addObject("authenticatedUserUsername",principal.getName());

        model.setViewName("logoutPage");
        return model;
    }

    @GetMapping("/registerPage")
    public String registerPage(Model model, Principal principal) {


        model.addAttribute("newUser",new Users());

        return "registerPage";
    }



    @PostMapping("/registerNewUser")
    public String registerNewUser(@ModelAttribute Users newUser, Model model, Principal principal) {


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = newUser.getPassword();

        newUser.setPassword(bCryptPasswordEncoder.encode(password));
        newUser.setEnabled(true);
        newUser.setFirstName(newUser.getUsername());
        newUser.setLastName(newUser.getUsername());
        newUser.setEmail(newUser.getUsername() + "@gmail.com");
        newUser.setCreated_date(new Date());


        Roles role = new Roles();
        role.setName(RolesType.ROLE_USER);

        newUser.setUsersRoles(new HashSet<>(Arrays.asList(role)));

        userRepository.save(newUser);
        return "redirect:/loginPage";
    }





//    public String getLoginPage(){
//        return "loginCard";
//    }
}
