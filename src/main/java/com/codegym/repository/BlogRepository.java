package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {
    Page<Blog> findAllByTitleContaining(String firstname, Pageable pageable);
    Page<Blog> findAllByPost(Post post, Pageable pageable);
}
