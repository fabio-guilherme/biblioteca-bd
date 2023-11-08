package br.edu.infnet.biblioteca.repository;

import br.edu.infnet.biblioteca.model.domain.Editora;
import br.edu.infnet.biblioteca.model.domain.Livro;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class LivroRepository {

    private JdbcTemplate jdbcTemplate;

    public LivroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Livro> listarTodos() throws SQLException {
        String sql = "SELECT l.id, l.titulo, l.ano, e.id AS editora_id, e.nome AS editora_nome, e.cidade AS editora_cidade"
                + " FROM livro l"
                + " JOIN editora e ON l.editora_id = e.id";
        return jdbcTemplate.query(sql, new RowMapper<Livro>() {
            @Override
            public Livro mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("ano"),
                        new Editora(
                                rs.getInt("editora_id"),
                                rs.getString("editora_nome"),
                                rs.getString("editora_cidade")
                        )
                );
            }
        });
    }

    public Livro buscarPorId(Long id) throws SQLException {
        String sql = "SELECT l.id, l.titulo, l.ano, e.id AS editora_id, e.nome AS editora_nome, e.cidade AS editora_cidade"
                + " FROM livro l"
                + " JOIN editora e ON l.editora_id = e.id"
                + " WHERE l.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Livro>() {
            @Override
            public Livro mapRow(ResultSet rs, int rowNum) throws SQLException {
                Livro livro = new Livro(
                        rs.getInt("l.id"),
                        rs.getString("l.titulo"),
                        rs.getInt("l.ano"),
                        new Editora(
                                rs.getInt("editora_id"),
                                rs.getString("editora_nome"),
                                rs.getString("editora_cidade")
                        )
                );

                return livro;
            }
        });
    }

    public void salvar(Livro livro) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO livro (titulo, ano, editora_id) VALUES (?, ?, ?)";
        //jdbcTemplate.update(sql, livro.getTitulo(), livro.getAno());
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, livro.getTitulo());
            pst.setInt(2, livro.getAno());
            pst.setInt(3, livro.getEditora().getId());
            return pst;
        }, keyHolder);
        livro.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    public void atualizar(Livro livro) throws SQLException {
        String sql = "UPDATE livro SET titulo = ?, ano = ?, editora_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, livro.getTitulo(), livro.getAno(), livro.getEditora().getId(), livro.getId());
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM livro WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}