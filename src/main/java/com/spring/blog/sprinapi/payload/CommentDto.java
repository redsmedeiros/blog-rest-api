package com.spring.blog.sprinapi.payload;

import lombok.Data;

@Data //cria getters e setters
public class CommentDto {

     //colocar os atributos que serão recebidos na requisição
     private long id;
     private String name;
     private String email;
     private String body;
}
