package com.desafio.agenda.service;

import com.desafio.agenda.dto.ContatoRequest;
import com.desafio.agenda.dto.ContatoResponse;
import com.desafio.agenda.exception.RecursoNaoEncontradoException;
import com.desafio.agenda.model.Contato;
import com.desafio.agenda.repository.ContatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public List<ContatoResponse> listarContatos() {
        try {
            List<ContatoResponse> contatos = contatoRepository.findAll().stream()
                    .map(ContatoResponse::new)
                    .collect(Collectors.toList());
            if (contatos.isEmpty()) {
                throw new RecursoNaoEncontradoException("Nenhum contato encontrado.");
            }
            return contatos;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao listar contatos: " + ex.getMessage());
        }
    }

    public ContatoResponse cadastrarContato(ContatoRequest request) {
        try {
            if (contatoRepository.findByContatoCelular(request.getContatoCelular()).isPresent()) {
                throw new IllegalArgumentException("Contato já cadastrado com este celular.");
            }
            Contato contato = new Contato();
            contato.setContatoNome(request.getContatoNome());
            contato.setContatoEmail(request.getContatoEmail());
            contato.setContatoCelular(request.getContatoCelular());
            contato.setContatoTelefone(request.getContatoTelefone());
            contato.setContatoSnFavorito('N');
            contato.setContatoSnAtivo('S');
            contato.setContatoDhCad(java.time.LocalDateTime.now());
            contatoRepository.save(contato);
            return new ContatoResponse(contato);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao cadastrar contato: " + ex.getMessage());
        }
    }

    @Transactional
    public void atualizarContato(Long id, ContatoRequest request) {
        try {
            Contato contato = contatoRepository.findById(id)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Contato não encontrado."));
            contato.setContatoNome(request.getContatoNome());
            contato.setContatoEmail(request.getContatoEmail());
            contato.setContatoTelefone(request.getContatoTelefone());
            if (request.getContatoSnFavorito() != '\0') {
                contato.setContatoSnFavorito(request.getContatoSnFavorito());
            } else {
                throw new IllegalArgumentException("O campo contatoSnFavorito não pode ser vazio.");
            }
            contatoRepository.save(contato);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar contato: " + ex.getMessage());
        }
    }

    public void inativarContato(Long id) {
        try {
            Contato contato = contatoRepository.findById(id)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Contato não encontrado."));
            contato.setContatoSnAtivo('N'); 
            contatoRepository.save(contato); 
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inativar contato: " + ex.getMessage());
        }
    }
    
}
