package com.spring.blog.sprinapi.service;

import java.util.List;

import com.spring.blog.sprinapi.payload.PostDto;


public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getlAllPosts();

    PostDto getPostById(long id);
}
