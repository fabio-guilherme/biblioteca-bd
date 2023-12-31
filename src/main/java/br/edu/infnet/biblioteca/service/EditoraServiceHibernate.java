package br.edu.infnet.biblioteca.service;

import br.edu.infnet.biblioteca.model.domain.Editora;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EditoraServiceHibernate extends EditoraService {

    private final SessionFactory sessionFactory;

    public EditoraServiceHibernate() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
    }

    public Editora buscarPorId(int id) {
        Session sessao = sessionFactory.openSession();
        Editora editora = sessao.get(Editora.class, id);
        sessao.close();
        return editora;
    }

    public List<Editora> buscarTodas() {
        Session sessao = sessionFactory.openSession();
        List<Editora> editoras = sessao.createQuery("FROM Editora", Editora.class).list();
        sessao.close();
        return editoras;
    }

    public Editora salvar(Editora editora) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.persist(editora);
        sessao.getTransaction().commit();
        sessao.close();
        return editora;
    }

    public Editora atualizar(Editora editora) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.merge(editora);
        sessao.getTransaction().commit();
        sessao.close();
        return editora;
    }

    public void excluir(Editora editora) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.remove(editora);
        sessao.getTransaction().commit();
        sessao.close();
    }
}