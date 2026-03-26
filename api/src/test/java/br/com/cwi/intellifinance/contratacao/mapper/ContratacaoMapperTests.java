package br.com.cwi.intellifinance.contratacao.mapper;

import br.com.cwi.intellifinance.contratacao.factory.ContratacaoFactory;
import br.com.cwi.intellifinance.controller.request.CriarContratacaoRequest;
import br.com.cwi.intellifinance.controller.response.CriarContratacaoResponse;
import br.com.cwi.intellifinance.domain.enitty.Contratacao;
import br.com.cwi.intellifinance.domain.enitty.Produto;
import br.com.cwi.intellifinance.domain.enums.StatusContratacao;
import br.com.cwi.intellifinance.mapper.ContratacaoMapper;
import br.com.cwi.intellifinance.produto.factory.ProdutoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ContratacaoMapper")
class ContratacaoMapperTests {

    @Nested
    @DisplayName("toEntity")
    class ToEntity {

        @Test
        @DisplayName("deve mapear todos os campos corretamente")
        void toEntityDeveMapearTodosOsCamposCorretamente() {
            CriarContratacaoRequest request = ContratacaoFactory.buildRequest();
            Produto produto = ProdutoFactory.build();
            BigDecimal valorParcela = new BigDecimal("924.95");
            BigDecimal custoTotal = new BigDecimal("11099.40");

            Contratacao contratacao = ContratacaoMapper.toEntity(request, produto, valorParcela, custoTotal);

            assertThat(contratacao.getClienteId()).isEqualTo(request.clienteId());
            assertThat(contratacao.getProduto()).isEqualTo(produto);
            assertThat(contratacao.getProdutoVersao()).isEqualTo(produto.getVersao());
            assertThat(contratacao.getValorContratado()).isEqualTo(request.valorContratado());
            assertThat(contratacao.getPrazoMeses()).isEqualTo(request.prazoMeses());
            assertThat(contratacao.getTaxaJurosMensal()).isEqualTo(produto.getTaxaJurosMensal());
            assertThat(contratacao.getValorParcela()).isEqualTo(valorParcela);
            assertThat(contratacao.getCustoTotal()).isEqualTo(custoTotal);
        }

        @Test
        @DisplayName("deve definir status como PENDENTE")
        void toEntityDeveDefinirStatusComoPendente() {
            CriarContratacaoRequest request = ContratacaoFactory.buildRequest();
            Produto produto = ProdutoFactory.build();

            Contratacao contratacao = ContratacaoMapper.toEntity(request, produto, BigDecimal.TEN, BigDecimal.TEN);

            assertThat(contratacao.getStatus()).isEqualTo(StatusContratacao.PENDENTE);
        }
    }

    @Nested
    @DisplayName("toCriarResponse")
    class ToCriarResponse {

        @Test
        @DisplayName("deve mapear todos os campos corretamente")
        void toCriarResponseDeveMapearTodosOsCamposCorretamente() {
            Contratacao contratacao = ContratacaoFactory.build();

            CriarContratacaoResponse response = ContratacaoMapper.toCriarResponse(contratacao);

            assertThat(response.id()).isEqualTo(contratacao.getId());
            assertThat(response.clienteId()).isEqualTo(contratacao.getClienteId());
            assertThat(response.produtoId()).isEqualTo(contratacao.getProduto().getId());
            assertThat(response.produtoNome()).isEqualTo(contratacao.getProduto().getNome());
            assertThat(response.produtoVersao()).isEqualTo(contratacao.getProdutoVersao());
            assertThat(response.valorContratado()).isEqualTo(contratacao.getValorContratado());
            assertThat(response.prazoMeses()).isEqualTo(contratacao.getPrazoMeses());
            assertThat(response.taxaJurosMensal()).isEqualTo(contratacao.getTaxaJurosMensal());
            assertThat(response.valorParcela()).isEqualTo(contratacao.getValorParcela());
            assertThat(response.custoTotal()).isEqualTo(contratacao.getCustoTotal());
            assertThat(response.status()).isEqualTo(contratacao.getStatus());
            assertThat(response.criadoEm()).isEqualTo(contratacao.getCriadoEm());
        }
    }
}