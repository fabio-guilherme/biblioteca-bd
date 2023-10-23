package br.edu.infnet.biblioteca;

import br.edu.infnet.biblioteca.biblioteca.model.domain.Editora;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateTeste {
    public static void teste() {
        // Configuração da sessão do Hibernate
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();

        // Abertura da sessão
        Session session = factory.openSession();
        session.beginTransaction();

        // Exemplo de consulta
        List<Editora> editoras = session.createQuery("FROM Editora").list();
        for (Editora editora : editoras) {
            System.out.println("Nome da Editora: " + editora.getNome());
        }

        // Commit da transação e fechamento da sessão
        session.getTransaction().commit();
        session.close();
    }
}