CREATE DATABASE IF NOT EXISTS rinha_de_galo;

USE rinha_de_galo;

CREATE TABLE IF NOT EXISTS galos (
    id_galo INT PRIMARY KEY,
    raca_galo VARCHAR(255),
    poder_de_combate INT,
    names VARCHAR(255),
    life INT,
    senha VARCHAR(255)
);