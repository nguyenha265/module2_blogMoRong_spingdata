package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Post;
import com.codegym.service.BlogService;
import com.codegym.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/post")
    public ModelAndView postFindAll(@RequestParam("category") Optional<String> category, Pageable pageable) {
        Page<Post> posts;
        if (category.isPresent()) {
            posts = postService.findAllByCategoryContaining(category.get(), pageable);
        } else {
            posts = postService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/post/list");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @GetMapping("/post/create")
    public ModelAndView createPostFrom() {
        ModelAndView modelAndView = new ModelAndView("/post/create");
        modelAndView.addObject("post", new Post());
        return modelAndView;
    }

    @PostMapping("/post/create-post")
    public ModelAndView createPost(@ModelAttribute("post") Post post) {
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("/post/create");
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("message", "Create new Post success");
        return modelAndView;
    }

    @GetMapping("/post/delete/{id}")
    public ModelAndView delelePostFrom(@PathVariable("id") Long id) {
        Post post = postService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/post/delete");
        modelAndView.addObject("posts", post);
        return modelAndView;
    }

    @PostMapping("/post-delete")
    public ModelAndView deletePost(@ModelAttribute("post") Post post) {
        postService.remove(post.getId());
        ModelAndView modelAndView = new ModelAndView("/post/delete");
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("message", "Delete Post success");
        return modelAndView;
    }

    @GetMapping("/post/edit/{id}")
    public ModelAndView editPostFrom(@PathVariable("id") Long id) {
        Post post = postService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/post/edit");
        modelAndView.addObject("post", post);
        return modelAndView;
    }

    @PostMapping("/post-edit")
    public ModelAndView editPost(@ModelAttribute("post") Post post) {
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("/post/edit");
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("message", "update Post success");
        return modelAndView;
    }

    @GetMapping("/post/view/{id}")
    public ModelAndView viewCatagory(@PathVariable("id") Long id, Pageable pageable) {
        Post post = postService.findById(id);
        if (post == null) {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
        Page<Blog> blog = blogService.findAllByPost(post, pageable);
        ModelAndView modelAndView = new ModelAndView("/post/view");
        modelAndView.addObject("post", post);
        modelAndView.addObject("blogs", blog);
        return modelAndView;
    }
}
