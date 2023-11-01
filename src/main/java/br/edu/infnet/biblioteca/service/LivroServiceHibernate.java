package br.edu.infnet.biblioteca.service;

import br.edu.infnet.biblioteca.model.domain.Livro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class LivroServiceHibernate {

    private final SessionFactory sessionFactory;

    public LivroServiceHibernate() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
    }

    public Livro buscarPorId(int id) {
        Session session = sessionFactory.openSession();
        Livro livro = session.get(Livro.class, id);
        session.close();
        return livro;
    }

    public List<Livro> buscarTodos() {
        Session session = sessionFactory.openSession();
        List<Livro> livros = session.createQuery("FROM Livro", Livro.class).list();
        session.close();
        return livros;
    }

    public void salvar(Livro livro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(livro);
        session.getTransaction().commit();
        session.close();
    }

    public void atualizar(Livro livro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(livro);
        session.getTransaction().commit();
        session.close();
    }

    public void excluir(Livro livro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(livro);
        session.getTransaction().commit();
        session.close();
    }
}
