package br.edu.infnet.biblioteca;

import br.edu.infnet.biblioteca.model.domain.Editora;
import br.edu.infnet.biblioteca.service.EditoraService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateTeste {

    public static void teste() {
        System.out.println("Início do teste");

        EditoraService editoraService = new EditoraService();
        listaEditoras("Estado inicial", editoraService);

        // Cria uma nova editora
        Editora editora = new Editora();
        editora.setNome("Editora C");
        editora.setCidade("Cidade C");

        // Salva a editora
        editoraService.save(editora);
        listaEditoras("Nova editora", editoraService);

        // Atualiza a editora
        editora.setNome("Editora D");
        editoraService.update(editora);
        listaEditoras("Atualiza editora", editoraService);

        // Deleta a editora
        editoraService.delete(editora);
        listaEditoras("Deleta editora", editoraService);

        System.out.println("Fim do teste");
    }

    private static void listaEditoras(String message, EditoraService editoraService) {
        System.out.println(message);
        // Lista todas as editoras
        List<Editora> editoras = editoraService.findAll();
        for (Editora editora : editoras) {
            System.out.println("Nome da Editora: " + editora.getNome());
        }
        System.out.println();
    }

    public static void testeOld() {
        System.out.println("Início do teste");

        // Configuração da sessão do Hibernate
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();

        // Abertura da sessão
        Session session = factory.openSession();
        session.beginTransaction();

        // Exemplo de consulta
        List<Editora> editoras = session.createQuery("FROM Editora", Editora.class).list();
        for (Editora editora : editoras) {
            System.out.println("Nome da Editora: " + editora.getNome());
        }
        System.out.println("Fim do teste");

        // Commit da transação e fechamento da sessão
        session.getTransaction().commit();
        session.close();
    }
}