package br.edu.infnet.biblioteca.repository;

import br.edu.infnet.biblioteca.model.domain.Autor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AutorRepository {

    private JdbcTemplate jdbcTemplate;

    public AutorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Autor> listarTodos() throws SQLException {
        String sql = "SELECT id, nome, nascimento FROM autor";
        return jdbcTemplate.query(sql, new RowMapper<Autor>() {
            @Override
            public Autor mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Autor(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getDate("nascimento")
                );
            }
        });
    }

    public Autor buscarPorId(Long id) throws SQLException {
        String sql = "SELECT id, nome, nascimento FROM autor WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Autor>() {
            @Override
            public Autor mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Autor(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getDate("nascimento")
                );
            }
        });
    }

    public void salvar(Autor autor) throws SQLException {
        String sql = "INSERT INTO autor (nome, nascimento) VALUES (?, ?)";
        jdbcTemplate.update(sql, autor.getNome(), autor.getNascimento());
    }

    public void atualizar(Autor autor) throws SQLException {
        String sql = "UPDATE autor SET nome = ?, nascimento = ? WHERE id = ?";
        jdbcTemplate.update(sql, autor.getNome(), autor.getNascimento(), autor.getId());
    }

    public void excluir(Long id) throws SQLException {
        String sql = "DELETE FROM autor WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
