package com.spring.blog.sprinapi.service;


import com.spring.blog.sprinapi.payload.PostDto;
import com.spring.blog.sprinapi.payload.PostResponse;


public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getlAllPosts(int pageNo, int pageSize);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostByid(long id);
}
