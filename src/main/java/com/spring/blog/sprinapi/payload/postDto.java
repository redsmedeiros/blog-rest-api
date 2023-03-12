package com.spring.blog.sprinapi.payload;

import lombok.Data;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Data
public class PostDto {
    
    private Long id;

    @NotEmpty
    @Size( min = 4, message = "Title shoud have at least 4 characteres")
    private String title;

    @NotEmpty
    @Size( min = 10, message = "Should have at least 10 characteres")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments; //faz retornar um array com os comentários na chamada
}
