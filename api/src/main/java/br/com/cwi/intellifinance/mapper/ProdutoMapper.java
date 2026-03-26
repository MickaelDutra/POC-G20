package br.com.cwi.intellifinance.mapper;

import br.com.cwi.intellifinance.controller.response.DetalharProdutoResponse;
import br.com.cwi.intellifinance.controller.response.ListarProdutoResponse;
import br.com.cwi.intellifinance.domain.enitty.Produto;

public class ProdutoMapper {

    public static ListarProdutoResponse toListarResponse(Produto produto) {

        return new ListarProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getDescricaoHumanizada(),
                produto.getTaxaJurosMensal(),
                produto.getValorMinimo(),
                produto.getValorMaximo()
        );
    }

    public static DetalharProdutoResponse toDetalharResponse(Produto produto) {

        return new DetalharProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getVersao(),
                produto.getDescricaoTecnica(),
                produto.getDescricaoHumanizada(),
                produto.getTaxaJurosMensal(),
                produto.getValorMinimo(),
                produto.getValorMaximo(),
                produto.getPrazoMinimoMeses(),
                produto.getPrazoMaximoMeses(),
                produto.isAtivo()
        );
    }
}