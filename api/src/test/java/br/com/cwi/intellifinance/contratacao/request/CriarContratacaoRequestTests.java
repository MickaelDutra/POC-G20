package br.com.cwi.intellifinance.contratacao.request;


import br.com.cwi.intellifinance.controller.request.CriarContratacaoRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CriarContratacaoRequest")
class CriarContratacaoRequestTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private CriarContratacaoRequest buildValido() {
        return new CriarContratacaoRequest(1L, 1L, new BigDecimal("10000.00"), 12);
    }

    @Nested
    @DisplayName("validação geral")
    class ValidacaoGeral {

        @Test
        @DisplayName("deve passar na validação com dados válidos")
        void validarDevePassarNaValidacaoComDadosValidos() {
            Set<ConstraintViolation<CriarContratacaoRequest>> violations = validator.validate(buildValido());

            assertThat(violations).isEmpty();
        }

        @Test
        @DisplayName("deve lançar ConstraintViolation para todos os campos quando todos forem nulos")
        void validarDeveLancarConstraintViolationParaTodosOsCamposQuandoTodosForemNulos() {
            CriarContratacaoRequest request = new CriarContratacaoRequest(null, null, null, null);

            Set<ConstraintViolation<CriarContratacaoRequest>> violations = validator.validate(request);

            assertThat(violations).hasSize(4);
        }
    }

    @Nested
    @DisplayName("clienteId")
    class ClienteId {

        @Test
        void validarDeveLancarConstraintViolationQuandoClienteIdForNulo() {
            CriarContratacaoRequest request = new CriarContratacaoRequest(null, 1L, new BigDecimal("10000.00"), 12);

            Set<ConstraintViolation<CriarContratacaoRequest>> violations = validator.validate(request);

            assertThat(violations)
                    .extracting(ConstraintViolation::getMessage)
                    .contains("O campo clienteId é obrigatório.");
        }
    }

    @Nested
    @DisplayName("produtoId")
    class ProdutoId {

        @Test
        @DisplayName("deve lançar ConstraintViolation com mensagem correta quando nulo")
        void validarDeveLancarConstraintViolationQuandoProdutoIdForNulo() {
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, null, new BigDecimal("10000.00"), 12);

            Set<ConstraintViolation<CriarContratacaoRequest>> violations = validator.validate(request);

            assertThat(violations).hasSize(1);
            assertThat(violations.iterator().next().getMessage())
                    .isEqualTo("O campo produtoId é obrigatório.");
        }
    }

    @Nested
    @DisplayName("valorContratado")
    class ValorContratado {

        @Test
        @DisplayName("deve lançar ConstraintViolation com mensagem correta quando nulo")
        void validarDeveLancarConstraintViolationQuandoValorContratadoForNulo() {
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, null, 12);

            Set<ConstraintViolation<CriarContratacaoRequest>> violations = validator.validate(request);

            assertThat(violations).hasSize(1);
            assertThat(violations.iterator().next().getMessage())
                    .isEqualTo("O campo valorContratado é obrigatório.");
        }

        @Test
        @DisplayName("deve lançar ConstraintViolation com mensagem correta quando zero")
        void validarDeveLancarConstraintViolationQuandoValorContratadoForZero() {
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, BigDecimal.ZERO, 12);

            Set<ConstraintViolation<CriarContratacaoRequest>> violations = validator.validate(request);

            assertThat(violations).hasSize(1);
            assertThat(violations.iterator().next().getMessage())
                    .isEqualTo("O valor contratado deve ser maior que zero.");
        }

        @Test
        @DisplayName("deve lançar ConstraintViolation com mensagem correta quando negativo")
        void validarDeveLancarConstraintViolationQuandoValorContratadoForNegativo() {
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("-100.00"), 12);

            Set<ConstraintViolation<CriarContratacaoRequest>> violations = validator.validate(request);

            assertThat(violations).hasSize(1);
            assertThat(violations.iterator().next().getMessage())
                    .isEqualTo("O valor contratado deve ser maior que zero.");
        }
    }

    @Nested
    @DisplayName("prazoMeses")
    class PrazoMeses {

        @Test
        @DisplayName("deve lançar ConstraintViolation com mensagem correta quando nulo")
        void validarDeveLancarConstraintViolationQuandoPrazoMesesForNulo() {
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("10000.00"), null);

            Set<ConstraintViolation<CriarContratacaoRequest>> violations = validator.validate(request);

            assertThat(violations).hasSize(1);
            assertThat(violations.iterator().next().getMessage())
                    .isEqualTo("O campo prazoMeses é obrigatório.");
        }

        @Test
        @DisplayName("deve lançar ConstraintViolation com mensagem correta quando zero")
        void validarDeveLancarConstraintViolationQuandoPrazoMesesForZero() {
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("10000.00"), 0);

            Set<ConstraintViolation<CriarContratacaoRequest>> violations = validator.validate(request);

            assertThat(violations).hasSize(1);
            assertThat(violations.iterator().next().getMessage())
                    .isEqualTo("O prazo em meses deve ser maior que zero.");
        }

        @Test
        @DisplayName("deve lançar ConstraintViolation com mensagem correta quando negativo")
        void validarDeveLancarConstraintViolationQuandoPrazoMesesForNegativo() {
            CriarContratacaoRequest request = new CriarContratacaoRequest(1L, 1L, new BigDecimal("10000.00"), -1);

            Set<ConstraintViolation<CriarContratacaoRequest>> violations = validator.validate(request);

            assertThat(violations).hasSize(1);
            assertThat(violations.iterator().next().getMessage())
                    .isEqualTo("O prazo em meses deve ser maior que zero.");
        }
    }
}