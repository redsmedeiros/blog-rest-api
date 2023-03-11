package com.spring.blog.sprinapi.service.impl;

import com.spring.blog.sprinapi.entity.Comment;
import com.spring.blog.sprinapi.entity.Post;
import com.spring.blog.sprinapi.exception.BlogApiException;
import com.spring.blog.sprinapi.payload.CommentDto;
import com.spring.blog.sprinapi.repository.CommentRepository;
import com.spring.blog.sprinapi.repository.PostRepository;
import com.spring.blog.sprinapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.spring.blog.sprinapi.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {

        //buscar os comentarios pelo id do post
        List<Comment> comments = commentRepository.findByPostId(postId);

        //transformar cada obj do array em um obj de requisição
        return comments.stream().map( comment -> matToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        //buscar o posto que contem esse comentario
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId) );

        //buscar o comentario no banco
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));

        System.out.println(comment.getPost());
        System.out.println(comment.getPost().getId());
        System.out.println(post.getId());

        //verifica se o ID do post do comentário é igual ao ID do post fornecido
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return matToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {

        //pegar a postagem do comentario no banco atraves do id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

        //pegar o comentario atraves do id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId) );

        //verificar se o comentario pertence a postagem
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        //atualizar o objeto de comentarios
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        //salvar no banco
        Comment commentUpdate = commentRepository.save(comment);

        return matToDto(commentUpdate);
    }

    @Override
    public void deleteComment(long postId, long commentId) {

        //buscar o post no banco através do id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId) );

        //buscar o comentario no banco atraves do id
        Comment comment = commentRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

        //verificar se o comentario pertence ao id
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        commentRepository.delete(comment);
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
