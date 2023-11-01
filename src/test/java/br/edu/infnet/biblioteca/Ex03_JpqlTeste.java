package br.edu.infnet.biblioteca;

import br.edu.infnet.biblioteca.model.domain.Editora;
import br.edu.infnet.biblioteca.model.domain.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class Ex03_JpqlTeste {

    @PersistenceContext
    private static EntityManager em;

    public static void teste() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "biblioteca" );
        em = emfactory.createEntityManager();

        todasEditoras();
        todosLivros();
    }

    private static void todasEditoras() {
        System.out.println("\nTestando: todasEditoras");
        List<Editora> editoras =  em.createQuery("SElECT object(e) FROM Editora e").getResultList();
        for (Editora editora : editoras) {
            System.out.println("Id: " + editora.getId() + " - Nome da Editora: " + editora.getNome());
        }
    }
    private static void todosLivros() {
        System.out.println("\nTestando: todosLivros");
        List<Livro> livros =  em.createQuery("SElECT l FROM Livro l").getResultList();
        for (Livro livro : livros) {
            System.out.println("Id: " + livro.getId()
                                + " - Nome do Livro: " + livro.getTitulo()
                                + " - Editora: " + livro.getEditora().getNome());
        }
    }
}