package br.com.cwi.intellifinance.repository;

import br.com.cwi.intellifinance.domain.enitty.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByAtivoTrue();
}
