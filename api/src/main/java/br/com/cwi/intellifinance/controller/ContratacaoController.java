package br.com.cwi.intellifinance.controller;

import br.com.cwi.intellifinance.controller.request.CriarContratacaoRequest;
import br.com.cwi.intellifinance.controller.response.CriarContratacaoResponse;
import br.com.cwi.intellifinance.service.ContratacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contratacoes")
public class ContratacaoController {

    private final ContratacaoService contratacaoService;

    @PostMapping
    public ResponseEntity<CriarContratacaoResponse> criar(@RequestBody @Valid CriarContratacaoRequest request) {
        CriarContratacaoResponse contratacao = contratacaoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(contratacao);
    }
}