package com.spring.blog.sprinapi.payload;

import lombok.Data;
import java.util.Set;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments; //faz retornar um array com os comentários na chamada
}
