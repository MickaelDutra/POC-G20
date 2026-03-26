package br.com.cwi.intellifinance.controller.response;

import br.com.cwi.intellifinance.domain.enums.StatusContratacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CriarContratacaoResponse(
        Long id,
        Long clienteId,
        Long produtoId,
        String produtoNome,
        Integer produtoVersao,
        BigDecimal valorContratado,
        Integer prazoMeses,
        BigDecimal taxaJurosMensal,
        BigDecimal valorParcela,
        BigDecimal custoTotal,
        StatusContratacao status,
        LocalDateTime criadoEm
) {}