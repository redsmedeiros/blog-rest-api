package com.spring.blog.sprinapi.service;

import com.spring.blog.sprinapi.payload.PostDto;


public interface PostService {

    PostDto createPost(PostDto postDto);
}
