package br.edu.infnet.biblioteca;

//import java.sql.*; -> Forma alternativa, importando todas as bibliotecas que comecem com java.sql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BdTeste {
    public static void main(String[] arg) {
        Connection connection;
        try {
            // As duas linhas abaixo são usadas para conectividade.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/infnet_biblioteca", "root", "password");

            // infnet_biblioteca é o banco de dados
            // root é o usuário do banco de dados
            // password is a senha do banco de dados

            Statement statement;
            statement = connection.createStatement();

            mostraLivros(statement);
            alteraLivro(statement);
            incluiLivro(statement);
            mostraLivros(statement);

            statement.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void mostraLivros(Statement statement) throws Exception {
        ResultSet resultSet;
        resultSet = statement.executeQuery("select * from livro");

        int id;
        int ano;
        String titulo;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            ano = resultSet.getInt("ano");
            titulo = resultSet.getString("titulo").trim();
            System.out.println("Id: " + id + " Ano:" + ano + " Título: " + titulo);
        }
        System.out.println();
        resultSet.close();
    }

    private static void alteraLivro(Statement statement) throws Exception {
        // Altera livros cujo Título comece com "Livro " e seja seguido por um caracter qualquer.
        // O caracter "_" na cláusula LIKE representa um único caracter naquela posição.
        String command = "UPDATE Livro"
                        + " SET ano = ano - 5"
                        + " WHERE Titulo LIKE 'Livro _'";

        statement.executeUpdate(command);
    }
    private static void incluiLivro(Statement statement) throws Exception {
        // Inclui um novo livro - campo id não é especificado por ser gerado automaticamente no banco de dados.
        String command = "INSERT INTO livro (titulo, ano, editora_id)"
                + " VALUES ('Aprendendo Java', 2023, 2)";

        statement.executeUpdate(command);
    }
}