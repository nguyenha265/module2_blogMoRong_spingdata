package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Page<Post> findAllByCategoryContaining(String categoty, Pageable pageable);

}
