package com.desafio.agenda.controller;

import com.desafio.agenda.dto.ContatoRequest;
import com.desafio.agenda.dto.ContatoResponse;
import com.desafio.agenda.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v3/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @Operation(summary = "Lista todos os contatos cadastrados.", description = "Retorna uma lista com todos os contatos do sistema.")
    @GetMapping
    public ResponseEntity<?> listarContatos() {
        try {
            List<ContatoResponse> contatos = contatoService.listarContatos();
            return ResponseEntity.ok(contatos);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createMessageResponse("Erro ao listar contatos: " + ex.getMessage()));
        }
    }

    @Operation(summary = "Cadastra um novo contato.", description = "Adiciona um novo contato ao sistema.")
    @PostMapping
    public ResponseEntity<?> cadastrarContato(@RequestBody ContatoRequest request) {
        try {
            ContatoResponse contato = contatoService.cadastrarContato(request);
            Map<String, String> mensagem = new HashMap<>();
            mensagem.put("mensagem", "Contato cadastrado com sucesso! ID: " + contato.getContatoId());
            return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(createMessageResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createMessageResponse("Erro ao cadastrar contato: " + ex.getMessage()));
        }
    }

    @Operation(summary = "Atualiza um contato existente.", description = "Permite atualizar as informações de um contato existente pelo ID.")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarContato(@PathVariable Long id, @RequestBody ContatoRequest request) {
        try {
            contatoService.atualizarContato(id, request);
            return ResponseEntity.ok(createMessageResponse("Contato atualizado com sucesso."));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(createMessageResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createMessageResponse("Erro ao atualizar contato: " + ex.getMessage()));
        }
    }

    @Operation(summary = "Inativa um contato.", description = "Marca um contato como inativo pelo ID.")
    @PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativarContato(@PathVariable Long id) {
        try {
            contatoService.inativarContato(id);
            return ResponseEntity.ok(createMessageResponse("Contato inativado com sucesso."));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(createMessageResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createMessageResponse("Erro ao inativar contato: " + ex.getMessage()));
        }
    }

  
    private Map<String, String> createMessageResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", message);
        return response;
    }
}
