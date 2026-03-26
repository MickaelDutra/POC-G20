package br.com.cwi.intellifinance.produto.factory;

import br.com.cwi.intellifinance.domain.enitty.Produto;
import br.com.cwi.intellifinance.domain.enums.CategoriaProduto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoFactory {

    public static Produto build() {
        return Produto.builder()
                .id(1L)
                .nome("Empréstimo Pessoal")
                .categoria(CategoriaProduto.EMPRESTIMO_PESSOAL)
                .versao(1)
                .descricaoTecnica("Descrição técnica do produto")
                .descricaoHumanizada("Descrição humanizada do produto")
                .taxaJurosMensal(new BigDecimal("0.0199"))
                .valorMinimo(new BigDecimal("1000.00"))
                .valorMaximo(new BigDecimal("50000.00"))
                .prazoMinimoMeses(6)
                .prazoMaximoMeses(48)
                .ativo(true)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();
    }

    public static Produto buildSemLimites() {
        return build().toBuilder()
                .valorMinimo(null)
                .valorMaximo(null)
                .prazoMinimoMeses(null)
                .prazoMaximoMeses(null)
                .build();
    }

    public static Produto buildInativo() {
        return build().toBuilder()
                .ativo(false)
                .build();
    }
}