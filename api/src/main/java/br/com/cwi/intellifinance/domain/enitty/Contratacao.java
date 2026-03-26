package br.com.cwi.intellifinance.domain.enitty;

import br.com.cwi.intellifinance.domain.enums.StatusContratacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "contratacao")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Contratacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private Integer produtoVersao;
    private BigDecimal valorContratado;
    private Integer prazoMeses;
    private BigDecimal taxaJurosMensal;
    private BigDecimal valorParcela;
    private BigDecimal custoTotal;
    @Enumerated(EnumType.STRING)
    private StatusContratacao status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime criadoEm;
    @LastModifiedDate
    private LocalDateTime atualizadoEm;
}