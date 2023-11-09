package br.edu.infnet.biblioteca;

import br.edu.infnet.biblioteca.service.EditoraServiceHibernate;
import br.edu.infnet.biblioteca.service.EditoraServiceJpa;

import java.sql.SQLException;

public class Main {
    public static void main(String[] arg) {
        // Comentar os testes que não quiser fazer

        // Ex01_JdbcTeste.teste();                                               // Teste de JDBC (1a. semana)

        /*
            Instanciando os serviços para Hibernate e para JPA
            Descomentar os imports correspondentes acima quando for usar os serviços
         */
        EditoraServiceHibernate editoraServiceHibernate = new EditoraServiceHibernate();
        EditoraServiceJpa editoraServiceJpa = new EditoraServiceJpa();
        // Eecutando os testes para Hibernate e JPA
        Ex02_HibernateJpaTeste.testeEditora(editoraServiceHibernate);        // Teste Hibernate (2a. semana)
        // Ex02_HibernateJpaTeste.testeEditora(editoraServiceJpa);              // Teste Hibernate (2a. semana)

        //Ex03_JpqlTeste.teste();

        /*
        try {
            Ex04_JdbcTemplateTeste.testeLivro();                                // Teste Hibernate (3a. semana)
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        */
    }
}