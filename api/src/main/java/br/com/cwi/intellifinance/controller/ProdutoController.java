package br.com.cwi.intellifinance.controller;

import br.com.cwi.intellifinance.controller.response.DetalharProdutoResponse;
import br.com.cwi.intellifinance.controller.response.ListarProdutoResponse;
import br.com.cwi.intellifinance.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ListarProdutoResponse>> listar() {
        List<ListarProdutoResponse> produtos = produtoService.listarAtivos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharProdutoResponse> detalhar(@PathVariable Long id) {
        DetalharProdutoResponse produto = produtoService.detalhar(id);
        return ResponseEntity.ok(produto);
    }
}