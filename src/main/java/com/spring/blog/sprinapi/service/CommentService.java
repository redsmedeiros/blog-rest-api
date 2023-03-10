package com.spring.blog.sprinapi.service;

import com.spring.blog.sprinapi.payload.CommentDto;

public interface CommentService {

    //metodo interface para criar comentarios
    CommentDto createComment(long postId, CommentDto commentDto);
}
