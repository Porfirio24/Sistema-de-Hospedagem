CREATE DATABASE IF NOT EXISTS pousada;
USE pousada;

drop table hospedes;

CREATE TABLE hospedes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14),
    telefone VARCHAR(20),
    email VARCHAR(100)
);

drop table funcionarios;

CREATE TABLE funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cargo VARCHAR(50),
    login VARCHAR(50),
    senha VARCHAR(100)
);

drop table quartos;

CREATE TABLE quartos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    tipo VARCHAR(50),
    preco DOUBLE,
    status VARCHAR(20) DEFAULT 'disponivel'
);

CREATE TABLE reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_hospede INT,
    id_quarto INT,
    data_entrada DATE,
    data_saida DATE,
    valor_total DOUBLE,
    FOREIGN KEY (id_hospede) REFERENCES hospedes(id),
    FOREIGN KEY (id_quarto) REFERENCES quartos(id)
);