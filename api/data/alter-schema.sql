DROP INDEX IF EXISTS idx_produto_busca;

ALTER TABLE produto
DROP CONSTRAINT IF EXISTS ck_produto_renda_minima,
    DROP COLUMN IF EXISTS perfil_indicado,
    DROP COLUMN IF EXISTS renda_minima,
    DROP COLUMN IF EXISTS criterios_elegibilidade,
    DROP COLUMN IF EXISTS variaveis_simulacao,

ALTER TABLE contratacao
DROP CONSTRAINT IF EXISTS fk_contratacao_simulacao,
    DROP COLUMN IF EXISTS simulacao_id CASCADE,
    DROP COLUMN IF EXISTS sessao_id;