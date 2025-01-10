CREATE SCHEMA IF NOT EXISTS desafio;

CREATE TABLE desafio.contato (
    contato_id SERIAL PRIMARY KEY,
    contato_nome VARCHAR(100) NOT NULL,
    contato_email VARCHAR(255),
    contato_celular VARCHAR(11) UNIQUE NOT NULL,
    contato_telefone VARCHAR(10),
    contato_sn_favorito CHAR(1) DEFAULT 'N',
    contato_sn_ativo CHAR(1) DEFAULT 'S',
    contato_dh_cad TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
