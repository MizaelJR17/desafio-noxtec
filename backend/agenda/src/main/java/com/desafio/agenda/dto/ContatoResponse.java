package com.desafio.agenda.dto;

import com.desafio.agenda.model.Contato;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoResponse {
    private Long contatoId;
    private String contatoNome;
    private String contatoCelular;
    private String contatoEmail;
    private String contatoTelefone;
    private char contatoSnFavorito;
    private char contatoSnAtivo;

  
    public ContatoResponse(Contato contato) {
        this.contatoId = contato.getContatoId();
        this.contatoNome = contato.getContatoNome();
        this.contatoCelular = contato.getContatoCelular();
        this.contatoEmail = contato.getContatoEmail();
        this.contatoTelefone = contato.getContatoTelefone();
        this.contatoSnFavorito = contato.getContatoSnFavorito();
        this.contatoSnAtivo = contato.getContatoSnAtivo();
    }
}
