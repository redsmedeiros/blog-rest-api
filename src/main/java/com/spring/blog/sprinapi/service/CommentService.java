package com.spring.blog.sprinapi.service;

import com.spring.blog.sprinapi.payload.CommentDto;

import java.util.List;

public interface CommentService {

    //metodo interface para criar comentarios
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);
}
