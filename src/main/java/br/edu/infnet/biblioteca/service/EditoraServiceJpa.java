package br.edu.infnet.biblioteca.service;

import br.edu.infnet.biblioteca.model.domain.Editora;
import jakarta.persistence.*;

import java.util.List;

public class EditoraServiceJpa extends EditoraService {

    @PersistenceContext
    private EntityManager entityManager;

    public EditoraServiceJpa() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "biblioteca" );
        this.entityManager = emfactory.createEntityManager();
    }

    public List<Editora> buscarTodas() {
        return entityManager.createQuery("SELECT e FROM Editora e", Editora.class).getResultList();
    }

    public Editora buscarPorId(int id) {
        return entityManager.find(Editora.class, id);
    }

    public Editora salvar(Editora editora) {
        entityManager.getTransaction( ).begin( );
        entityManager.persist(editora);
        entityManager.getTransaction( ).commit( );
        return editora;
    }

    public Editora atualizar(Editora editora) {
        entityManager.getTransaction( ).begin( );
        entityManager.merge(editora);
        entityManager.getTransaction( ).commit( );
        return editora;
    }

    public void excluir(Editora editora) {
        entityManager.getTransaction( ).begin( );
        entityManager.remove(editora);
        entityManager.getTransaction( ).commit( );
    }
}