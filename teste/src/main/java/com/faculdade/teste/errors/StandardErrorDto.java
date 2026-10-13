package com.faculdade.teste.errors;

import java.time.Instant;
import java.util.List;

public record StandardErrorDto(
        Instant timeStamp,
        Integer status,
        String erro,
        String mensagem,
        List<ValidationErrorDto> validacoes
) {

    public StandardErrorDto(Instant timeStamp, Integer status, String erro, String mensagem) {
        this(timeStamp, status, erro, mensagem, null);
    }
}
