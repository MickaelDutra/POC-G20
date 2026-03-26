package br.com.cwi.intellifinance.contratacao.factory;

import br.com.cwi.intellifinance.controller.request.CriarContratacaoRequest;
import br.com.cwi.intellifinance.domain.enitty.Contratacao;
import br.com.cwi.intellifinance.domain.enums.StatusContratacao;
import br.com.cwi.intellifinance.produto.factory.ProdutoFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContratacaoFactory {

    public static CriarContratacaoRequest buildRequest() {
        return new CriarContratacaoRequest(
                1L,
                1L,
                new BigDecimal("10000.00"),
                12
        );
    }

    public static Contratacao build() {
        return Contratacao.builder()
                .id(1L)
                .clienteId(1L)
                .produto(ProdutoFactory.build())
                .produtoVersao(1)
                .valorContratado(new BigDecimal("10000.00"))
                .prazoMeses(12)
                .taxaJurosMensal(new BigDecimal("0.0199"))
                .valorParcela(new BigDecimal("924.95"))
                .custoTotal(new BigDecimal("11099.40"))
                .status(StatusContratacao.PENDENTE)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();
    }
}