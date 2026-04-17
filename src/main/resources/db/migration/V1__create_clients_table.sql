CREATE SEQUENCE IF NOT EXISTS clients_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE clients (
                         id BIGINT PRIMARY KEY DEFAULT nextval('clients_seq'),
                         deal_id VARCHAR(50) NOT NULL UNIQUE,
                         full_name VARCHAR(255) NOT NULL,
                         inn VARCHAR(12) NOT NULL UNIQUE,
                         CONSTRAINT inn_format_check CHECK (inn ~ '^\d{10}$|^\d{12}$')
);

COMMENT ON TABLE clients IS 'Клиенты';
COMMENT ON COLUMN clients.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN clients.deal_id IS 'Идентификатор сделки (уникальный)';
COMMENT ON COLUMN clients.full_name IS 'Полное имя клиента';
COMMENT ON COLUMN clients.inn IS 'ИНН клиента (10 или 12 цифр)';