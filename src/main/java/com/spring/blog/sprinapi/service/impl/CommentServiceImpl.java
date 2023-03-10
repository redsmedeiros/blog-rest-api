package com.spring.blog.sprinapi.service.impl;

import com.spring.blog.sprinapi.entity.Comment;
import com.spring.blog.sprinapi.entity.Post;
import com.spring.blog.sprinapi.payload.CommentDto;
import com.spring.blog.sprinapi.repository.CommentRepository;
import com.spring.blog.sprinapi.repository.PostRepository;
import com.spring.blog.sprinapi.service.CommentService;
import org.springframework.stereotype.Service;
import com.spring.blog.sprinapi.exception.ResourceNotFoundException;

@Service
public class CommentServiceImpl implements CommentService {

    //instanciar o repositorio
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    //metodos do serviço
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        //Instanciar obj comment com os dados da requisição
        Comment comment = mapToEntity(commentDto);

        //procurar no banco o post que tem esse comentario
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

        //colocar no obt comment a postagem
        comment.setPost(post);

        //salvar o obj comment no banco
        Comment newComment = commentRepository.save(comment);

        //retornar o objto da requisição
        return matToDto(newComment);
    }




    //metodos para converter os objtos de requisição
    private CommentDto matToDto(Comment comment){

        //intanciar o obj de requisição
        CommentDto commentDto = new CommentDto();

        //criar o objto com os valores
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){

        Comment comment = new Comment();

        comment.setId(commentDto.getId());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());

        return comment;
    }
}
