package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Post findById(Long id);
    void save(Post post);
    void remove(Long id);
    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByCategoryContaining(String categoty, Pageable pageable);

}
