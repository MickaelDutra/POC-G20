package br.com.cwi.intellifinance.service;

import br.com.cwi.intellifinance.controller.request.CriarContratacaoRequest;
import br.com.cwi.intellifinance.controller.response.CriarContratacaoResponse;
import br.com.cwi.intellifinance.domain.enitty.Contratacao;
import br.com.cwi.intellifinance.domain.enitty.Produto;
import br.com.cwi.intellifinance.exception.BusinessException;
import br.com.cwi.intellifinance.mapper.ContratacaoMapper;
import br.com.cwi.intellifinance.repository.ContratacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Service
public class ContratacaoService {

    private final ContratacaoRepository contratacaoRepository;
    private final ProdutoService produtoService;

    public CriarContratacaoResponse criar(CriarContratacaoRequest request) {
        Produto produto = produtoService.findById(request.produtoId());

        validarValorContratado(request.valorContratado(), produto);
        validarPrazo(request.prazoMeses(), produto);

        BigDecimal taxa = produto.getTaxaJurosMensal();
        BigDecimal valorParcela = calcularParcela(request.valorContratado(), taxa, request.prazoMeses());
        BigDecimal custoTotal = valorParcela.multiply(BigDecimal.valueOf(request.prazoMeses()));

        Contratacao contratacao = ContratacaoMapper.toEntity(request, produto, valorParcela, custoTotal);
        contratacaoRepository.save(contratacao);

        CriarContratacaoResponse response = ContratacaoMapper.toCriarResponse(contratacao);
        return response;
    }

    private void validarValorContratado(BigDecimal valorContratado, Produto produto) {

        if (produto.getValorMinimo() != null && valorContratado.compareTo(produto.getValorMinimo()) < 0) {
            throw new BusinessException("O valor contratado é inferior ao mínimo permitido de " + produto.getValorMinimo() + ".");
        }

        if (produto.getValorMaximo() != null && valorContratado.compareTo(produto.getValorMaximo()) > 0) {
            throw new BusinessException("O valor contratado é superior ao máximo permitido de " + produto.getValorMaximo() + ".");
        }
    }

    private void validarPrazo(Integer prazoMeses, Produto produto) {

        if (produto.getPrazoMinimoMeses() != null && prazoMeses < produto.getPrazoMinimoMeses()) {
            throw new BusinessException("O prazo é inferior ao mínimo permitido de " + produto.getPrazoMinimoMeses() + " meses.");
        }

        if (produto.getPrazoMaximoMeses() != null && prazoMeses > produto.getPrazoMaximoMeses()) {
            throw new BusinessException("O prazo é superior ao máximo permitido de " + produto.getPrazoMaximoMeses() + " meses.");
        }
    }

    private BigDecimal calcularParcela(BigDecimal valorContratado, BigDecimal taxaMensal, Integer prazoMeses) {
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

        BigDecimal um = BigDecimal.ONE;
        BigDecimal umMaisTaxa = um.add(taxaMensal);
        BigDecimal fator = umMaisTaxa.pow(-prazoMeses, mc);
        BigDecimal denominador = um.subtract(fator);

        return valorContratado.multiply(taxaMensal, mc)
                .divide(denominador, 2, RoundingMode.HALF_UP);
    }
}