package br.com.cwi.intellifinance.domain.enitty;

import br.com.cwi.intellifinance.domain.enums.CategoriaProduto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "produto")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;
    private Integer versao;
    private String descricaoTecnica;
    private String descricaoHumanizada;
    private BigDecimal taxaJurosMensal;
    private BigDecimal valorMinimo;
    private BigDecimal valorMaximo;
    private Integer prazoMinimoMeses;
    private Integer prazoMaximoMeses;
    private boolean ativo;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime criadoEm;
    @LastModifiedDate
    private LocalDateTime atualizadoEm;
}