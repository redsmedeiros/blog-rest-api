package com.spring.blog.sprinapi.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.blog.sprinapi.entity.Post;
import com.spring.blog.sprinapi.payload.PostDto;
import com.spring.blog.sprinapi.repository.PostRepository;
import com.spring.blog.sprinapi.service.PostService;
import com.spring.blog.sprinapi.exception.ResourceNotFoundException;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);
     
        //salvar atraves do repository no banco
        Post newPost = postRepository.save(post);

        //instanciar novo objto dto para montar o objeto de response
        PostDto postResponse = matToDto(newPost);

        //retornar a resposta
        return postResponse;
    }

    @Override
    public List<PostDto> getlAllPosts() {
        
        List<Post> posts = postRepository.findAll();

        return posts.stream().map( post -> matToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
      
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        return matToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {

        //pegar do db um registo com esse id e vetificar a existencia
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));

        //salvar a atualização com o obj dto vindo da requisição
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        //salvar no db a atualização
        Post updatePost = postRepository.save(post);

        return matToDto(updatePost);

    }

    @Override
    public void deletePostByid(long id) {
        //pegar do db um registo com esse id e vetificar a existencia
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));

        postRepository.delete(post);
    }




    //metodo para converter o entity em dto
    private PostDto matToDto(Post post){

        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        postDto. setTitle(post.getTitle());

        return postDto;

    }

    //metodo para converter dto em entity
    private Post mapToEntity(PostDto postDto){

        //instanciar novo objto post
        Post post = new Post();
        //colocar os valores no objtos usando o dto
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;

    }

   

   

   

    
}
