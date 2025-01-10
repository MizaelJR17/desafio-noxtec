package com.desafio.agenda.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class AgendamentoDTO {
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String cliente;

    @Pattern(regexp = "^\\d{10,11}$", message = "O telefone deve ter 10 ou 11 dígitos")
    private String telefone;

    @NotBlank(message = "A data do agendamento é obrigatória")
    private String data;
}
