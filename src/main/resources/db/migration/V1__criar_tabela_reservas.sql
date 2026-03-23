CREATE TABLE reservas (
    id BIGSERIAL PRIMARY KEY,
    data_evento DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fim TIME NOT NULL,
    nome_requerente VARCHAR(255) NOT NULL,
    cpf_cnpj VARCHAR(18) NOT NULL,
    email VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);