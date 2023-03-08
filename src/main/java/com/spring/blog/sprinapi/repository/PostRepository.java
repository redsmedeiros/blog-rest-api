package com.spring.blog.sprinapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.spring.blog.sprinapi.entity.Post;


public interface PostRepository extends JpaRepository<Post, Long> {
}
