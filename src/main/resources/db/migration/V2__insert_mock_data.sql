TRUNCATE TABLE client_deals, clients RESTART IDENTITY CASCADE;

INSERT INTO clients (id, full_name, inn) VALUES
    ('CLT-00123', 'Иванов Иван Иванович', '1234567890'),
    ('CLT-00124', 'Петров Петр Петрович', '098765432109'),
    ('CLT-00125', 'Сидорова Анна Сергеевна', '1122334455');

INSERT INTO client_deals (deal_id, client_id) VALUES
    ('CRD-2025-00123', (SELECT id FROM clients WHERE inn = '1234567890')),
    ('CRD-2025-00124', (SELECT id FROM clients WHERE inn = '1234567890')),
    ('CRD-2025-00125', (SELECT id FROM clients WHERE inn = '098765432109')),
    ('CRD-2025-00126', (SELECT id FROM clients WHERE inn = '1122334455'));