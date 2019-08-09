package com.codegym.blogformater;

import com.codegym.model.Post;
import com.codegym.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PostFormaster implements Formatter<Post> {
    @Autowired
    private PostService postService;

    public PostFormaster(PostService postService) {
        this.postService = postService;
    }

    @Override
    public Post parse(String text, Locale locale) throws ParseException {
        return postService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Post object, Locale locale) {
        return "[" + object.getId() + ", " + object.getCategory() + "]";
    }
}
