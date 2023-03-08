package com.spring.blog.sprinapi.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.blog.sprinapi.payload.PostDto;
import com.spring.blog.sprinapi.repository.PostRepository;
import com.spring.blog.sprinapi.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPost'");
    }

    
}
