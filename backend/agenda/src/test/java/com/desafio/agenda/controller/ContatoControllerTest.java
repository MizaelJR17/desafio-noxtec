package com.desafio.agenda.controller;

import com.desafio.agenda.dto.ContatoRequest;
import com.desafio.agenda.dto.ContatoResponse;
import com.desafio.agenda.service.ContatoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.desafio.agenda.model.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContatoController.class)
public class ContatoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContatoService contatoService;

    @Test
    public void listarContatos_deveRetornarListaVazia() throws Exception {
        Mockito.when(contatoService.listarContatos()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/v3/contatos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }
    
    @Test
    public void cadastrarContato_deveRetornarContatoCriado() throws Exception {
        Contato contato = new Contato();
        contato.setContatoId(1L);
        contato.setContatoNome("João");
        contato.setContatoCelular("11999999999");
        contato.setContatoEmail("joao@gmail.com");
        contato.setContatoTelefone("1133224455");
        contato.setContatoSnFavorito('N');
        contato.setContatoSnAtivo('S');
    
        ContatoResponse response = new ContatoResponse(contato);
    
        Mockito.when(contatoService.cadastrarContato(any(ContatoRequest.class))).thenReturn(response);
    
        mockMvc.perform(post("/api/v3/contatos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"contatoNome\": \"João\", \"contatoCelular\": \"11999999999\", \"contatoEmail\": \"joao@gmail.com\", \"contatoTelefone\": \"1133224455\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.contatoNome").value("João"))
                .andExpect(jsonPath("$.contatoCelular").value("11999999999"));
    }
    
    @Test
    public void atualizarContato_deveAtualizarComSucesso() throws Exception {
        Mockito.doNothing().when(contatoService).atualizarContato(eq(1L), any(ContatoRequest.class));

        mockMvc.perform(put("/api/v3/contatos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"contatoNome\": \"João\", \"contatoCelular\": \"11999999999\", \"contatoEmail\": \"joao@gmail.com\", \"contatoTelefone\": \"1133224455\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Contato atualizado com sucesso."));
    }

    @Test
    public void inativarContato_deveInativarComSucesso() throws Exception {
        Mockito.doNothing().when(contatoService).inativarContato(1L);

        mockMvc.perform(delete("/api/v3/contatos/1"))
                .andExpect(status().isNoContent());
    }
}
