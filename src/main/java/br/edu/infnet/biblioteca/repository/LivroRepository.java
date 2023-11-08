package br.edu.infnet.biblioteca.repository;

import br.edu.infnet.biblioteca.model.domain.Livro;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class LivroRepository {

    private JdbcTemplate jdbcTemplate;

    public LivroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Livro> listarTodos() throws SQLException {
        String sql = "SELECT id, titulo, ano FROM livro";
        return jdbcTemplate.query(sql, new RowMapper<Livro>() {
            @Override
            public Livro mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("ano")
                );
            }
        });
    }

    public Livro buscarPorId(Long id) throws SQLException {
        String sql = "SELECT id, titulo, ano FROM livro WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Livro>() {
            @Override
            public Livro mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("ano")
                );
            }
        });
    }

    public void salvar(Livro livro) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO livro (titulo, ano) VALUES (?, ?)";
        //jdbcTemplate.update(sql, livro.getTitulo(), livro.getAno());
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, livro.getTitulo());
            pst.setInt(2, livro.getAno());
            return pst;
        }, keyHolder);
        livro.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    public void atualizar(Livro livro) throws SQLException {
        String sql = "UPDATE livro SET titulo = ?, ano = ? WHERE id = ?";
        jdbcTemplate.update(sql, livro.getTitulo(), livro.getAno(), livro.getId());
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM livro WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}