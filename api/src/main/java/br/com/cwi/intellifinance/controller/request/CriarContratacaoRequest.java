package br.com.cwi.intellifinance.controller.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CriarContratacaoRequest(

        @NotNull(message = "O campo clienteId é obrigatório.")
        Long clienteId,
        @NotNull(message = "O campo produtoId é obrigatório.")
        Long produtoId,
        @NotNull(message = "O campo valorContratado é obrigatório.")
        @DecimalMin(value = "0.01", message = "O valor contratado deve ser maior que zero.")
        BigDecimal valorContratado,
        @NotNull(message = "O campo prazoMeses é obrigatório.")
        @Min(value = 1, message = "O prazo em meses deve ser maior que zero.")
        Integer prazoMeses
) {}