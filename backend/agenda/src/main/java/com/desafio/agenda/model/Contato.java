package com.desafio.agenda.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "coontato", schema = "desafio") 
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contatoId;

    @Column(nullable = false)
    private String contatoNome;

    private String contatoEmail;

    @Column(unique = true, nullable = false)
    private String contatoCelular;

    private String contatoTelefone;

    @Column(nullable = false)
    private char contatoSnFavorito;

    @Column(nullable = false)
    private char contatoSnAtivo;

    @Column(nullable = false)
    private LocalDateTime contatoDhCad;
}
