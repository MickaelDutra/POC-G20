package br.com.cwi.intellifinance.domain.enitty;

import br.com.cwi.intellifinance.domain.enums.CategoriaProduto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

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
    private String perfilIndicado;
    private BigDecimal rendaMinima;
    private BigDecimal taxaJurosMensal;
    private BigDecimal valorMinimo;
    private BigDecimal valorMaximo;
    private Integer prazoMinimoMeses;
    private Integer prazoMaximoMeses;
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> criteriosElegibilidade;
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> variaveisSimulacao;
    private Boolean ativo;

    @CreatedDate
    @Column(updatable = false)
    private ZonedDateTime criadoEm;
    @LastModifiedDate
    private ZonedDateTime atualizadoEm;
}