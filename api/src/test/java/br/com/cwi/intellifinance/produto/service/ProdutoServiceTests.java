package br.com.cwi.intellifinance.produto.service;


import br.com.cwi.intellifinance.controller.response.DetalharProdutoResponse;
import br.com.cwi.intellifinance.controller.response.ListarProdutoResponse;
import br.com.cwi.intellifinance.domain.enitty.Produto;
import br.com.cwi.intellifinance.exception.ResourceNotFoundException;
import br.com.cwi.intellifinance.produto.factory.ProdutoFactory;
import br.com.cwi.intellifinance.repository.ProdutoRepository;
import br.com.cwi.intellifinance.service.ProdutoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProdutoService")
class ProdutoServiceTests {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Nested
    @DisplayName("listarAtivos")
    class ListarAtivos {

        @Test
        @DisplayName("deve retornar lista de produtos ativos")
        void listarAtivosDeveRetornarListaDeProdutosAtivos() {
            List<Produto> produtos = List.of(ProdutoFactory.build(), ProdutoFactory.build());
            when(produtoRepository.findAllByAtivoTrue()).thenReturn(produtos);

            List<ListarProdutoResponse> response = produtoService.listarAtivos();

            assertThat(response).hasSize(2);
            verify(produtoRepository).findAllByAtivoTrue();
        }

        @Test
        @DisplayName("deve retornar lista vazia quando não houver produtos ativos")
        void listarAtivosDeveRetornarListaVaziaQuandoNaoHouverProdutosAtivos() {
            when(produtoRepository.findAllByAtivoTrue()).thenReturn(List.of());

            List<ListarProdutoResponse> response = produtoService.listarAtivos();

            assertThat(response).isEmpty();
            verify(produtoRepository).findAllByAtivoTrue();
        }

        @Test
        @DisplayName("deve mapear corretamente os dados do produto")
        void listarAtivosDeveMapearCorretamenteOsDadosDoProduto() {
            Produto produto = ProdutoFactory.build();
            when(produtoRepository.findAllByAtivoTrue()).thenReturn(List.of(produto));

            List<ListarProdutoResponse> response = produtoService.listarAtivos();

            ListarProdutoResponse item = response.get(0);
            assertThat(item.id()).isEqualTo(produto.getId());
            assertThat(item.nome()).isEqualTo(produto.getNome());
            assertThat(item.categoria()).isEqualTo(produto.getCategoria());
        }
    }

    @Nested
    @DisplayName("detalhar")
    class Detalhar {

        @Test
        @DisplayName("deve retornar produto quando encontrado")
        void detalharDeveRetornarProdutoQuandoEncontrado() {
            Produto produto = ProdutoFactory.build();
            when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

            DetalharProdutoResponse response = produtoService.detalhar(1L);

            assertThat(response.id()).isEqualTo(produto.getId());
            assertThat(response.nome()).isEqualTo(produto.getNome());
            assertThat(response.versao()).isEqualTo(produto.getVersao());
            assertThat(response.ativo()).isEqualTo(produto.isAtivo());
            verify(produtoRepository).findById(1L);
        }

        @Test
        @DisplayName("deve lançar ResourceNotFoundException quando produto não encontrado")
        void detalharDeveLancarResourceNotFoundExceptionQuandoProdutoNaoEncontrado() {
            when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> produtoService.detalhar(99L))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessage("Produto não encontrado.");
        }
    }

    @Nested
    @DisplayName("findById")
    class FindById {

        @Test
        @DisplayName("deve retornar produto quando encontrado")
        void findByIdDeveRetornarProdutoQuandoEncontrado() {
            Produto produto = ProdutoFactory.build();
            when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

            Produto resultado = produtoService.findById(1L);

            assertThat(resultado).isEqualTo(produto);
            verify(produtoRepository).findById(1L);
        }

        @Test
        @DisplayName("deve lançar ResourceNotFoundException quando produto não encontrado")
        void findByIdDeveLancarResourceNotFoundExceptionQuandoProdutoNaoEncontrado() {
            when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> produtoService.findById(99L))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessage("Produto não encontrado.");
        }
    }
}