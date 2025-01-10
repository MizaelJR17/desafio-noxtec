package com.desafio.agenda.integration;

import com.desafio.agenda.model.Contato;
import com.desafio.agenda.repository.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ContatoIntegrationTest {

    @Autowired
    private ContatoRepository contatoRepository;

    @Test
    public void salvarContato_devePersistirNoBanco() {
        Contato contato = new Contato();
        contato.setContatoNome("João");
        contato.setContatoCelular("11999999999");
        contato.setContatoEmail("joao@gmail.com");

        Contato salvo = contatoRepository.save(contato);

        assertNotNull(salvo.getContatoId());
        assertEquals("João", salvo.getContatoNome());
    }

    @Test
    public void deletarContato_deveRemoverDoBanco() {
        Contato contato = new Contato();
        contato.setContatoNome("João");
        contato.setContatoCelular("11999999999");
        contato.setContatoEmail("joao@gmail.com");

        Contato salvo = contatoRepository.save(contato);
        contatoRepository.delete(salvo);

        assertFalse(contatoRepository.findById(salvo.getContatoId()).isPresent());
    }
}
