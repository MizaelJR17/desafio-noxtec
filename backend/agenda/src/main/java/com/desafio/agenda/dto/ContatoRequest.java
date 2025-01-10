package com.desafio.agenda.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class ContatoRequest {

    @NotBlank(message = "O nome do contato é obrigatório")
    private String contatoNome;

    @Pattern(regexp = "^\\d{11}$", message = "O celular deve ter exatamente 11 dígitos")
    private String contatoCelular;

    private String contatoEmail;

    @Pattern(regexp = "^\\d{10}$", message = "O telefone deve ter exatamente 10 dígitos")
    private String contatoTelefone;
    
    @Pattern(regexp = "[SN]", message = "O campo contatoSnFavorito deve ser 'S' ou 'N'")

    private char contatoSnFavorito;
}
