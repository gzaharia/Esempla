package com.esempla.blog.frontend.controller;

import com.esempla.blog.data.domain.*;
import com.esempla.blog.data.repository.BlogRepository;
import com.esempla.blog.data.repository.CategoryRepository;
import com.esempla.blog.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginCard {


    private final UserRepository userRepository;


    private final BlogRepository blogRepository;


    private final CategoryRepository categoryRepository;



    @GetMapping("/login-page")
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


    @GetMapping("/logout-page")
    public ModelAndView logoutPage(Principal principal) {
        ModelAndView model = new ModelAndView();

        model.addObject("authenticatedUserUsername",principal.getName());

        model.setViewName("logoutPage");
        return model;
    }

    @GetMapping("/register-page")
    public String registerPage(Model model) {


        model.addAttribute("newUser",new AppUser());

        return "registerPage";
    }



    @PostMapping("/register-new-user")
    public String registerNewUser(@ModelAttribute AppUser newUser) {


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

        return "redirect:/login-page";
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
}
