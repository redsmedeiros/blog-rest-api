package com.spring.blog.sprinapi.payload;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data //cria getters e setters
public class CommentDto {

     //colocar os atributos que serão recebidos na requisição
     private long id;

     @NotEmpty(message = "Name should not be null or empty")
     private String name;

     @NotEmpty(message = "Email should not be empty")
     @Email
     private String email;

     @NotEmpty(message = "Body should not be empty")
     @Size(min = 10, message = "At least 10 characteres")
     private String body;
}
