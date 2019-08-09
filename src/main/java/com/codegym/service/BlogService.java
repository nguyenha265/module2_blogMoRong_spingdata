package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Blog findById(Long id);

    void save(Blog blog);

    void remove(Long id);

    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllByTitleContaining(String firstname, Pageable pageable);

    Page<Blog> findAllByPost(Post post, Pageable pageable);
}
