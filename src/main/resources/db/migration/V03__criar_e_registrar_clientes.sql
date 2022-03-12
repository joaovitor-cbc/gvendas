CREATE TABLE cliente (
    codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    ativo BOOLEAN NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO cliente (nome, telefone, ativo) values ('João Silva', '(34) 99261-4270', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Maria Rita', '(34) 99261-4270', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Pedro Santos', '(34) 99261-4270', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Ricardo Pereira', '(34) 99261-4270', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Josué Mariano', '(34) 99261-4270', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Pedro Barbosa', '(34) 99261-4270', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Henrique Medeiros', '(34) 99261-4270', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Carlos Santana', '(34) 99261-4270', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Leonardo Oliveira', '(34) 99261-4270', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Isabela Martins', '(34) 99261-4270', true);