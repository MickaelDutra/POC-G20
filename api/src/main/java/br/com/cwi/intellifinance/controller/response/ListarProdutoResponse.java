package br.com.cwi.intellifinance.controller.response;

import br.com.cwi.intellifinance.domain.enums.CategoriaProduto;
import java.math.BigDecimal;

public record ListarProdutoResponse(
        Long id,
        String nome,
        CategoriaProduto categoria,
        String descricaoHumanizada,
        BigDecimal taxaJurosMensal,
        BigDecimal valorMinimo,
        BigDecimal valorMaximo
) {}