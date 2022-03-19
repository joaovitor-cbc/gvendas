CREATE TABLE cliente (
    codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

alter table  cliente add constraint UN_telefone UNIQUE (telefone);

INSERT INTO cliente (nome, telefone, ativo) values ('João Silva', '(34) 99261-4270', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Maria Rita', '(34) 99261-4271', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Pedro Santos', '(34) 99261-4272', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Ricardo Pereira', '(34) 99261-4273', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Josué Mariano', '(34) 99261-4274', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Pedro Barbosa', '(34) 99261-4275', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Henrique Medeiros', '(34) 99261-4276', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Carlos Santana', '(34) 99261-4278', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Leonardo Oliveira', '(34) 99261-4279', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Isabela Martins', '(34) 99261-4280', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Leonardo Oliveira', '(34) 99261-4277', true);
INSERT INTO cliente (nome, telefone, ativo) values ('Isabela Martins', '(34) 99261-4281', true);