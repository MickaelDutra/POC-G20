package br.com.cwi.intellifinance.controller.response;

import br.com.cwi.intellifinance.domain.enums.CategoriaProduto;

import java.math.BigDecimal;

public record DetalharProdutoResponse(
        Long id,
        String nome,
        CategoriaProduto categoria,
        Integer versao,
        String descricaoTecnica,
        String descricaoHumanizada,
        BigDecimal taxaJurosMensal,
        BigDecimal valorMinimo,
        BigDecimal valorMaximo,
        Integer prazoMinimoMeses,
        Integer prazoMaximoMeses,
        boolean ativo
) {}