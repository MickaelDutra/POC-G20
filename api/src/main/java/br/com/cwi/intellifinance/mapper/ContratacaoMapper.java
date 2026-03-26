package br.com.cwi.intellifinance.mapper;

import br.com.cwi.intellifinance.controller.request.CriarContratacaoRequest;
import br.com.cwi.intellifinance.controller.response.CriarContratacaoResponse;
import br.com.cwi.intellifinance.domain.enitty.Contratacao;
import br.com.cwi.intellifinance.domain.enitty.Produto;
import br.com.cwi.intellifinance.domain.enums.StatusContratacao;

import java.math.BigDecimal;

public class ContratacaoMapper {

    public static Contratacao toEntity(CriarContratacaoRequest request, Produto produto,
                                       BigDecimal valorParcela, BigDecimal custoTotal) {

        Contratacao contratacao = new Contratacao();
        contratacao.setClienteId(request.clienteId());
        contratacao.setProduto(produto);
        contratacao.setProdutoVersao(produto.getVersao());
        contratacao.setValorContratado(request.valorContratado());
        contratacao.setPrazoMeses(request.prazoMeses());
        contratacao.setTaxaJurosMensal(produto.getTaxaJurosMensal());
        contratacao.setValorParcela(valorParcela);
        contratacao.setCustoTotal(custoTotal);
        contratacao.setStatus(StatusContratacao.PENDENTE);
        return contratacao;
    }

    public static CriarContratacaoResponse toCriarResponse(Contratacao contratacao) {

        return new CriarContratacaoResponse(
                contratacao.getId(),
                contratacao.getClienteId(),
                contratacao.getProduto().getId(),
                contratacao.getProduto().getNome(),
                contratacao.getProdutoVersao(),
                contratacao.getValorContratado(),
                contratacao.getPrazoMeses(),
                contratacao.getTaxaJurosMensal(),
                contratacao.getValorParcela(),
                contratacao.getCustoTotal(),
                contratacao.getStatus(),
                contratacao.getCriadoEm()
        );
    }
}