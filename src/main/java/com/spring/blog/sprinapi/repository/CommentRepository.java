package com.spring.blog.sprinapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog.sprinapi.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

   
}
