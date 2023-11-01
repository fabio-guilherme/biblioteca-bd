package br.edu.infnet.biblioteca;

public class Main {
    public static void main(String[] arg) {
        // Comentar os testes que não quiser fazer

        //Ex01_JdbcTeste.teste();                                               // Teste de JDBC (1a. semana)

        // Instanciando os serviços para Hibernate e para JPA
        // EditoraServiceHibernate editoraServiceHibernate = new EditoraServiceHibernate();
        // EditoraServiceJpa editoraServiceJpa = new EditoraServiceJpa();
        // Eecutando os testes para Hibernate e JPA
        // Ex02_HibernateJpaTeste.testeEditora(editoraServiceHibernate);        // Teste Hibernate (2a. semana)
        // Ex02_HibernateJpaTeste.testeEditora(editoraServiceJpa);              // Teste Hibernate (2a. semana)

        Ex03_JpqlTeste.teste();
    }
}