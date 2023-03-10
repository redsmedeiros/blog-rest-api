package com.spring.blog.sprinapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //cria os getters e os setters
@AllArgsConstructor //instancia todos os atributos no construtor
@NoArgsConstructor //possibilita criar obj sem instanciar atributos
@Entity //cria a entidade da tabela
@Table(name = "comments")
public class Comment {

    //criar os atributos que irá modelar a tabela

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //o banco de dados é gera a chave primária automaticamente Essa estratégia é chamada de identidade, porque a chave primária é baseada em um identificador único para cada registro na tabela.
    private long id;

    //colunas das tabelas
    private String name;
    private String email;
    private String body;

    //instanciar a classe de Posts
    @ManyToOne(fetch = FetchType.LAZY) //multiplos comentarios pertence a uma postagem
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
