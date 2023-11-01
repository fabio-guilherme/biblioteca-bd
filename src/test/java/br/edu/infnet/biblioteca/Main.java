package br.edu.infnet.biblioteca;

import br.edu.infnet.biblioteca.service.EditoraServiceHibernate;
import br.edu.infnet.biblioteca.service.EditoraServiceJpa;

public class Main {
    public static void main(String[] arg) {
        // Comentar os testes que não quiser fazer
        //JdbcTeste.teste();            // Teste de JDBC (1a. semana)

        EditoraServiceHibernate editoraServiceHibernate = new EditoraServiceHibernate();
        EditoraServiceJpa editoraServiceJpa = new EditoraServiceJpa();

        HibernateJpaTeste.testeEditora(editoraServiceHibernate);    // Teste Hibernate (2a. semana)
        HibernateJpaTeste.testeEditora(editoraServiceJpa);          // Teste Hibernate (2a. semana)
    }
}