package com.desafio.agenda.repository;

import com.desafio.agenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Optional<Contato> findByContatoCelular(String celular);

    List<Contato> findByContatoSnAtivo(char ativo);

    List<Contato> findByContatoSnFavorito(char favorito);
}
