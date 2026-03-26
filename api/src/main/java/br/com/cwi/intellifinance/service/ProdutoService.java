package br.com.cwi.intellifinance.service;

import br.com.cwi.intellifinance.controller.response.DetalharProdutoResponse;
import br.com.cwi.intellifinance.controller.response.ListarProdutoResponse;
import br.com.cwi.intellifinance.domain.enitty.Produto;
import br.com.cwi.intellifinance.exception.ResourceNotFoundException;
import br.com.cwi.intellifinance.mapper.ProdutoMapper;
import br.com.cwi.intellifinance.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<ListarProdutoResponse> listarAtivos() {
        return produtoRepository.findAllByAtivoTrue().stream()
                .map(ProdutoMapper::toListarResponse)
                .toList();
    }

    public DetalharProdutoResponse detalhar(Long id) {
        Produto produto = findById(id);
        return ProdutoMapper.toDetalharResponse(produto);
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));
    }
}