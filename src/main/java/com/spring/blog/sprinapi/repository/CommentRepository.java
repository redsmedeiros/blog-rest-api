package com.spring.blog.sprinapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog.sprinapi.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);

   
}
