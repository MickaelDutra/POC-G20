package br.com.cwi.intellifinance.produto.mapper;

import br.com.cwi.intellifinance.controller.response.DetalharProdutoResponse;
import br.com.cwi.intellifinance.controller.response.ListarProdutoResponse;
import br.com.cwi.intellifinance.domain.enitty.Produto;
import br.com.cwi.intellifinance.mapper.ProdutoMapper;
import br.com.cwi.intellifinance.produto.factory.ProdutoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ProdutoMapper")
class ProdutoMapperTests {

    @Nested
    @DisplayName("toListarResponse")
    class ToListarResponse {

        @Test
        @DisplayName("deve mapear todos os campos corretamente")
        void toListarResponseDeveMapearTodosOsCamposCorretamente() {
            Produto produto = ProdutoFactory.build();

            ListarProdutoResponse response = ProdutoMapper.toListarResponse(produto);

            assertThat(response.id()).isEqualTo(produto.getId());
            assertThat(response.nome()).isEqualTo(produto.getNome());
            assertThat(response.categoria()).isEqualTo(produto.getCategoria());
            assertThat(response.descricaoHumanizada()).isEqualTo(produto.getDescricaoHumanizada());
            assertThat(response.taxaJurosMensal()).isEqualTo(produto.getTaxaJurosMensal());
            assertThat(response.valorMinimo()).isEqualTo(produto.getValorMinimo());
            assertThat(response.valorMaximo()).isEqualTo(produto.getValorMaximo());
        }
    }

    @Nested
    @DisplayName("toDetalharResponse")
    class ToDetalharResponse {

        @Test
        @DisplayName("deve mapear todos os campos corretamente")
        void toDetalharResponseDeveMapearTodosOsCamposCorretamente() {
            Produto produto = ProdutoFactory.build();

            DetalharProdutoResponse response = ProdutoMapper.toDetalharResponse(produto);

            assertThat(response.id()).isEqualTo(produto.getId());
            assertThat(response.nome()).isEqualTo(produto.getNome());
            assertThat(response.categoria()).isEqualTo(produto.getCategoria());
            assertThat(response.versao()).isEqualTo(produto.getVersao());
            assertThat(response.descricaoTecnica()).isEqualTo(produto.getDescricaoTecnica());
            assertThat(response.descricaoHumanizada()).isEqualTo(produto.getDescricaoHumanizada());
            assertThat(response.taxaJurosMensal()).isEqualTo(produto.getTaxaJurosMensal());
            assertThat(response.valorMinimo()).isEqualTo(produto.getValorMinimo());
            assertThat(response.valorMaximo()).isEqualTo(produto.getValorMaximo());
            assertThat(response.prazoMinimoMeses()).isEqualTo(produto.getPrazoMinimoMeses());
            assertThat(response.prazoMaximoMeses()).isEqualTo(produto.getPrazoMaximoMeses());
            assertThat(response.ativo()).isEqualTo(produto.isAtivo());
        }
    }
}