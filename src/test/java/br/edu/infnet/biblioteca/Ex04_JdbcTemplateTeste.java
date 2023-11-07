package br.edu.infnet.biblioteca;

import br.edu.infnet.biblioteca.model.domain.Autor;
import br.edu.infnet.biblioteca.repository.AutorRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Ex04_JdbcTemplateTeste {
    private static JdbcTemplate jdbcTemplate;

    public static void teste() throws SQLException {
        // Carregue o contexto de aplicação do Spring
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Obtenha o bean do repositório de Autor
        jdbcTemplate = new JdbcTemplate();
        AutorRepository autorRepository = context.getBean(AutorRepository.class);
        // AutorRepository autorRepository = new AutorRepository(jdbcTemplate);

        // Lista todos os autores
        listaAutores(autorRepository);

        // Busca um autor pelo ID
        System.out.println("\nTestando: buscando um autor");
        Autor autor = autorRepository.buscarPorId(1L);
        System.out.println(autor);

        // Salva um autor
        System.out.println("\nTestando: salvando um autor");
        Autor novoAutor = new Autor("Autor 4", new Date());
        autorRepository.salvar(novoAutor);
        listaAutores(autorRepository);

        // Atualiza um autor
        System.out.println("\nTestando: atualizando um autor");
        novoAutor.setNome("Autor Atualizado");
        autorRepository.atualizar(novoAutor);
        listaAutores(autorRepository);

        // Exclui um autor
        System.out.println("\nTestando: excluindo um autor");
        autorRepository.excluir(novoAutor.getId());
        listaAutores(autorRepository);
    }

    private static void listaAutores(AutorRepository autorRepository) throws SQLException {
        System.out.println("\nTestando: todos os autores");
        List<Autor> autores = autorRepository.listarTodos();
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }
}