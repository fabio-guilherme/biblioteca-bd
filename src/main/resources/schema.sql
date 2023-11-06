-- Apaga as tabelas caso já existam
DROP TABLE IF EXISTS livro_autor;
DROP TABLE IF EXISTS livro;
DROP TABLE IF EXISTS editora;
DROP TABLE IF EXISTS autor;

-- Cria a tabela editora
CREATE TABLE editora (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cidade VARCHAR(255)
);

-- Cria a tabela autor
CREATE TABLE autor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nascimento DATE
);

-- Cria a tabela livro
CREATE TABLE livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    ano INT,
    editora_id INT,
    FOREIGN KEY (editora_id) REFERENCES Editora(id)
);

-- Cria a tabela de junção livro_autor
CREATE TABLE livro_autor (
    livro_id INT,
    autor_id INT,
    FOREIGN KEY (livro_id) REFERENCES Livro(id),
    FOREIGN KEY (autor_id) REFERENCES Autor(id)
);

-- Insere dados de exemplo na tabela editora
INSERT INTO editora (nome, cidade) VALUES
    ('Editora A', 'Cidade A'),
    ('Editora B', 'Cidade B');

-- Insere dados de exemplo na tabela autor
INSERT INTO autor (nome, nascimento) VALUES
    ('Autor 1', '1990-01-01'),
    ('Autor 2', '1985-03-15'),
    ('Autor 3', '1978-07-20');

-- Insere dados de exemplo na tabela Livro
INSERT INTO livro (titulo, ano, editora_id) VALUES
    ('Livro 1', 2020, 1),
    ('Livro 2', 2018, 1),
    ('Livro 3', 2019, 2);

-- Insere dados de exemplo na tabela de junção livro_autor
INSERT INTO livro_autor (livro_id, autor_id) VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 3);
