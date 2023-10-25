package br.edu.infnet.biblioteca.service;

import br.edu.infnet.biblioteca.model.domain.Editora;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EditoraService {

    private SessionFactory sessionFactory;

    public EditoraService() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
    }

    public Editora findById(int id) {
        Session session = sessionFactory.openSession();
        Editora editora = session.get(Editora.class, id);
        session.close();
        return editora;
    }

    public List<Editora> findAll() {
        Session session = sessionFactory.openSession();
        List<Editora> editoras = session.createQuery("FROM Editora", Editora.class).list();
        session.close();
        return editoras;
    }

    public void save(Editora editora) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(editora);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Editora editora) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(editora);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Editora editora) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(editora);
        session.getTransaction().commit();
        session.close();
    }
}