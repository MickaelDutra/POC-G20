package br.com.cwi.intellifinance.contratacao.service;

import br.com.cwi.intellifinance.contratacao.factory.ContratacaoFactory;
import br.com.cwi.intellifinance.controller.request.CriarContratacaoRequest;
import br.com.cwi.intellifinance.controller.response.CriarContratacaoResponse;
import br.com.cwi.intellifinance.domain.enitty.Contratacao;
import br.com.cwi.intellifinance.domain.enitty.Produto;
import br.com.cwi.intellifinance.domain.enums.StatusContratacao;
import br.com.cwi.intellifinance.exception.BusinessException;
import br.com.cwi.intellifinance.exception.ResourceNotFoundException;
import br.com.cwi.intellifinance.produto.factory.ProdutoFactory;
import br.com.cwi.intellifinance.repository.ContratacaoRepository;
import br.com.cwi.intellifinance.service.ContratacaoService;
import br.com.cwi.intellifinance.service.ProdutoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ContratacaoService")
class ContratacaoServiceTests {

    @InjectMocks
    private ContratacaoService contratacaoService;

    @Mock
    private ContratacaoRepository contratacaoRepository;

    @Mock
    private ProdutoService produtoService;

    @Nested
    @DisplayName("criar")
    class Criar {

        @Test
        @DisplayName("deve salvar e retornar contratação com sucesso")
        void criarDeveSalvarERetornarContratacaoComSucesso() {
            CriarContratacaoRequest request = ContratacaoFactory.buildRequest();
            Produto produto = ProdutoFactory.build();
            when(produtoService.findById(request.produtoId())).thenReturn(produto);

            CriarContratacaoResponse response = contratacaoService.criar(request);

            assertThat(response).isNotNull();
            assertThat(response.valorContratado()).isEqualTo(request.valorContratado());
            assertThat(response.prazoMeses()).isEqualTo(request.prazoMeses());
            assertThat(response.status()).isEqualTo(StatusContratacao.PENDENTE);
            verify(contratacaoRepository).save(any(Contratacao.class));
        }

        @Test
        @DisplayName("deve calcular valorParcela e custoTotal corretamente")
        void criarDeveCalcularValorParcelaECustoTotalCorretamente() {
            CriarContratacaoRequest request = ContratacaoFactory.buildRequest();
            Produto produto = ProdutoFactory.build();
            when(produtoService.findById(request.produtoId())).thenReturn(produto);

            CriarContratacaoResponse response = contratacaoService.criar(request);

            assertThat(response.valorParcela()).isPositive();
            assertThat(response.custoTotal()).isPositive();
            assertThat(response.custoTotal()).isGreaterThan(request.valorContratado());
        }

        @Test
        @DisplayName("deve salvar contratação com os dados do request e do produto")
        void criarDeveSalvarContratacaoComOsDadosDoRequestEDoProduto() {
            CriarContratacaoRequest request = ContratacaoFactory.buildRequest();
            Produto produto = ProdutoFactory.build();
            when(produtoService.findById(request.produtoId())).thenReturn(produto);
            ArgumentCaptor<Contratacao> captor = ArgumentCaptor.forClass(Contratacao.class);

            contratacaoService.criar(request);

            verify(contratacaoRepository).save(captor.capture());
            Contratacao salva = captor.getValue();
            assertThat(salva.getClienteId()).isEqualTo(request.clienteId());
            assertThat(salva.getValorContratado()).isEqualTo(request.valorContratado());
            assertThat(salva.getPrazoMeses()).isEqualTo(request.prazoMeses());
            assertThat(salva.getStatus()).isEqualTo(StatusContratacao.PENDENTE);
            assertThat(salva.getTaxaJurosMensal()).isEqualTo(produto.getTaxaJurosMensal());
        }

        @Test
        @DisplayName("deve lançar ResourceNotFoundException quando produto não encontrado")
        void criarDeveLancarResourceNotFoundExceptionQuandoProdutoNaoEncontrado() {
            CriarContratacaoRequest request = ContratacaoFactory.buildRequest();
            when(produtoService.findById(request.produtoId()))
                    .thenThrow(new ResourceNotFoundException("Produto não encontrado."));

            assertThatThrownBy(() -> contratacaoService.criar(request))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessage("Produto não encontrado.");

            verify(contratacaoRepository, never()).save(any());
        }

        @Test
        @DisplayName("deve lançar BusinessException quando valor contratado for abaixo do mínimo")
        void criarDeveLancarBusinessExceptionQuandoValorContratadoAbaixoDoMinimo() {
            Produto produto = ProdutoFactory.build();
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("500.00"), 12);
            when(produtoService.findById(request.produtoId())).thenReturn(produto);

            assertThatThrownBy(() -> contratacaoService.criar(request))
                    .isInstanceOf(BusinessException.class)
                    .hasMessageContaining("inferior ao mínimo");

            verify(contratacaoRepository, never()).save(any());
        }

        @Test
        @DisplayName("deve lançar BusinessException quando valor contratado for acima do máximo")
        void criarDeveLancarBusinessExceptionQuandoValorContratadoAcimaDoMaximo() {
            Produto produto = ProdutoFactory.build();
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("99999.00"), 12);
            when(produtoService.findById(request.produtoId())).thenReturn(produto);

            assertThatThrownBy(() -> contratacaoService.criar(request))
                    .isInstanceOf(BusinessException.class)
                    .hasMessageContaining("superior ao máximo");

            verify(contratacaoRepository, never()).save(any());
        }

        @Test
        @DisplayName("deve lançar BusinessException quando prazo for abaixo do mínimo")
        void criarDeveLancarBusinessExceptionQuandoPrazoAbaixoDoMinimo() {
            Produto produto = ProdutoFactory.build();
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("10000.00"), 3);
            when(produtoService.findById(request.produtoId())).thenReturn(produto);

            assertThatThrownBy(() -> contratacaoService.criar(request))
                    .isInstanceOf(BusinessException.class)
                    .hasMessageContaining("inferior ao mínimo");

            verify(contratacaoRepository, never()).save(any());
        }

        @Test
        @DisplayName("deve lançar BusinessException quando prazo for acima do máximo")
        void criarDeveLancarBusinessExceptionQuandoPrazoAcimaDoMaximo() {
            Produto produto = ProdutoFactory.build();
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("10000.00"), 60);
            when(produtoService.findById(request.produtoId())).thenReturn(produto);

            assertThatThrownBy(() -> contratacaoService.criar(request))
                    .isInstanceOf(BusinessException.class)
                    .hasMessageContaining("superior ao máximo");

            verify(contratacaoRepository, never()).save(any());
        }

        @Test
        @DisplayName("deve permitir qualquer valor quando produto não possui valorMinimo")
        void criarDevePermitirQualquerValorQuandoProdutoNaoPossuiValorMinimo() {
            Produto produto = ProdutoFactory.buildSemLimites();
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("1.00"), 12);
            when(produtoService.findById(request.produtoId())).thenReturn(produto);

            CriarContratacaoResponse response = contratacaoService.criar(request);

            assertThat(response).isNotNull();
            verify(contratacaoRepository).save(any(Contratacao.class));
        }

        @Test
        @DisplayName("deve permitir qualquer valor quando produto não possui valorMaximo")
        void criarDevePermitirQualquerValorQuandoProdutoNaoPossuiValorMaximo() {
            Produto produto = ProdutoFactory.buildSemLimites();
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("999999999.00"), 12);
            when(produtoService.findById(request.produtoId())).thenReturn(produto);

            CriarContratacaoResponse response = contratacaoService.criar(request);

            assertThat(response).isNotNull();
            verify(contratacaoRepository).save(any(Contratacao.class));
        }

        @Test
        @DisplayName("deve permitir qualquer prazo quando produto não possui prazoMinimoMeses")
        void criarDevePermitirQualquerPrazoQuandoProdutoNaoPossuiPrazoMinimo() {
            Produto produto = ProdutoFactory.buildSemLimites();
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("10000.00"), 1);
            when(produtoService.findById(request.produtoId())).thenReturn(produto);

            CriarContratacaoResponse response = contratacaoService.criar(request);

            assertThat(response).isNotNull();
            verify(contratacaoRepository).save(any(Contratacao.class));
        }

        @Test
        @DisplayName("deve permitir qualquer prazo quando produto não possui prazoMaximoMeses")
        void criarDevePermitirQualquerPrazoQuandoProdutoNaoPossuiPrazoMaximo() {
            Produto produto = ProdutoFactory.buildSemLimites();
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("10000.00"), 9999);
            when(produtoService.findById(request.produtoId())).thenReturn(produto);

            CriarContratacaoResponse response = contratacaoService.criar(request);

            assertThat(response).isNotNull();
            verify(contratacaoRepository).save(any(Contratacao.class));
        }
    }
}