package br.edu.infnet.biblioteca.repository;

import br.edu.infnet.biblioteca.model.domain.Editora;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class EditoraRepository {

    private JdbcTemplate jdbcTemplate;

    public EditoraRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Editora> listarTodos() throws SQLException {
        String sql = "SELECT id, nome, cidade FROM editora";
        return jdbcTemplate.query(sql, new RowMapper<Editora>() {
            @Override
            public Editora mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Editora(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cidade")
                );
            }
        });
    }

    public Editora buscarPorId(Long id) throws SQLException {
        String sql = "SELECT id, nome, cidade FROM editora WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Editora>() {
            @Override
            public Editora mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Editora(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cidade")
                );
            }
        });
    }

    public void salvar(Editora editora) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO editora (nome, cidade) VALUES (?, ?)";
        //jdbcTemplate.update(sql, editora.getNome(), editora.getCidade());
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, editora.getNome());
            pst.setString(2, editora.getCidade());
            return pst;
        }, keyHolder);
        editora.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    public void atualizar(Editora editora) throws SQLException {
        String sql = "UPDATE editora SET nome = ?, cidade = ? WHERE id = ?";
        jdbcTemplate.update(sql, editora.getNome(), editora.getCidade(), editora.getId());
    }

    public void excluir(Long id) throws SQLException {
        String sql = "DELETE FROM editora WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
