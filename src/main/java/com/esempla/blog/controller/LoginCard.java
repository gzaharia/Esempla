package com.esempla.blog.controller;

import com.esempla.blog.domain.*;
import com.esempla.blog.repository.BlogRepository;
import com.esempla.blog.repository.CategoryRepository;
import com.esempla.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@Controller
public class LoginCard {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;



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


        model.addAttribute("newUser",new AppUser());

        return "registerPage";
    }



    @PostMapping("/registerNewUser")
    public String registerNewUser(@ModelAttribute AppUser newUser, Model model, Principal principal) {


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = newUser.getPassword();

        newUser.setPassword(bCryptPasswordEncoder.encode(password));
        newUser.setEnabled(true);
        newUser.setFirstName(newUser.getUsername());
        newUser.setLastName(newUser.getUsername());
        newUser.setEmail(newUser.getUsername() + "@gmail.com");
        newUser.setCreated_date(new Date());

        newUser = userRepository.save(newUser);

        Roles role = new Roles();
        role.setName(RolesType.ROLE_USER);

        Blog blogNew = new Blog();
        blogNew.setAppUser(newUser);
        blogNew.setName(newUser.getUsername() + " blog");
        blogNew.setCreated_date(LocalDate.now());

        newUser.setUsersRoles(new HashSet<>(Arrays.asList(role)));

        blogRepository.save(blogNew);

        return "redirect:/loginPage";
    }


    @ModelAttribute("allCategories")
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @ModelAttribute("authenticatedUserUsername")
    public String getAuthenticatedUserUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails ?
                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername() : "Guest";

    }




//    public String getLoginPage(){
//        return "loginCard";
//    }
}
