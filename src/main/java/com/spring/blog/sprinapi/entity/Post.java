package com.spring.blog.sprinapi.entity;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = { "title" })})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String content;

    //fazer a relação com a tabela de comentarios
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>(); //é uma estrutura de dados que armazena elementos únicos, ou seja, não há elementos duplicados dentro dela. 
}
