package com.esempla.blog.controller;


import com.esempla.blog.domain.Comments;
import com.esempla.blog.repository.AppUserRepository;
import com.esempla.blog.repository.CommentRepository;
import com.esempla.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;

@RequestMapping("comment")
@Controller
public class CommentController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private CommentRepository commentRepository;


    @PostMapping("/save")
    public String saveOrUpdate (@RequestParam(value = "postId",required = false) Long postId,
                                @RequestParam(value = "username",required = false) String username,
                                @ModelAttribute Comments comment , Model model , Principal principal){



        comment.setCreated_date(new Date());
        comment.setPost(postRepository.getOne(postId));
        comment.setAppUser(appUserRepository.findByUsername(username));
        commentRepository.save(comment);

        model.addAttribute("userDetails",principal);
        return "redirect:/appUser/homePage";
    }


}
