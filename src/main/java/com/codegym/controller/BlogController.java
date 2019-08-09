package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Post;
import com.codegym.service.BlogService;
import com.codegym.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private PostService postService;


    @ModelAttribute("posts")
    public Page<Post> findAll(Pageable pageable) {
        return postService.findAll(pageable);
    }

    @GetMapping("/blog")
    public ModelAndView listBlog(@RequestParam("s") Optional<String> s, @PageableDefault(size = 6) Pageable pageable) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContaining(s.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createBlogFrom() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public ModelAndView createBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "New Blog created successfully");
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewBlog(@PathVariable("id") Long id) {
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        modelAndView.addObject("blogs", blog);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editBlogFrom(@PathVariable("id") Long id) {
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping("/edit-blog")
    public ModelAndView editBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "update blog success");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteBlogFrom(@PathVariable("id") Long id) {
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/blog/delete");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping("/delete-blog")
    public ModelAndView deleteBlog(@ModelAttribute("blog") Blog blog) {
        blogService.remove(blog.getId());
        ModelAndView modelAndView = new ModelAndView("/blog/delete");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "Delete Blog Success");
        return modelAndView;
    }
}
