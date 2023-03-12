package com.spring.blog.sprinapi.service.impl;


import java.util.List;
import java.util.stream.Collectors;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.blog.sprinapi.entity.Post;
import com.spring.blog.sprinapi.payload.PostDto;
import com.spring.blog.sprinapi.payload.PostResponse;
import com.spring.blog.sprinapi.repository.PostRepository;
import com.spring.blog.sprinapi.service.PostService;
import com.spring.blog.sprinapi.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper){
        this.postRepository = postRepository;
        this.modelMapper = modelMapper; 
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
    public PostResponse getlAllPosts(int pageNo, int pageSize, String sortBY, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBY).ascending() : Sort.by(sortBY).descending();

        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts.stream().map( post -> matToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
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

        PostDto postDto = modelMapper.map(post, PostDto.class);

        return postDto;

    }

    //metodo para converter dto em entity
    private Post mapToEntity(PostDto postDto){

        Post post = modelMapper.map(postDto, Post.class);

        return post;

    }

   

   

   

    
}
