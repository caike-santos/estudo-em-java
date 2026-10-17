package com.faculdade.teste.dto;

public record EstatisticasResponseDto(
        long total,
        long pendentes,
        long emAndamento,
        long concluidas,
        long canceladas,
        long atrasadas,
        double percentualConclusao
) {
}
