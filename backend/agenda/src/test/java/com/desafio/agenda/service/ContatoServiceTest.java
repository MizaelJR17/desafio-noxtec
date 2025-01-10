package com.desafio.agenda.service;

import com.desafio.agenda.dto.ContatoRequest;
import com.desafio.agenda.dto.ContatoResponse;
import com.desafio.agenda.model.Contato;
import com.desafio.agenda.repository.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ContatoServiceTest {

    private final ContatoRepository contatoRepository = mock(ContatoRepository.class);
    private final ContatoService contatoService = new ContatoService(contatoRepository);

    @Test
    public void cadastrarContato_deveSalvarContato() {
        ContatoRequest request = new ContatoRequest();
        request.setContatoNome("João");
        request.setContatoCelular("11999999999");

        Contato contato = new Contato();
        contato.setContatoId(1L);
        contato.setContatoNome("João");
        contato.setContatoCelular("11999999999");

        when(contatoRepository.findByContatoCelular("11999999999")).thenReturn(Optional.empty());
        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);

        ContatoResponse response = contatoService.cadastrarContato(request);

        assertNotNull(response);
        assertEquals("João", response.getContatoNome());
    }

    @Test
    public void inativarContato_deveLancarExcecaoSeNaoExistir() {
        when(contatoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> contatoService.inativarContato(1L));
        assertEquals("Contato não encontrado.", exception.getMessage());
    }
}
