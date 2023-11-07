package br.edu.infnet.biblioteca.model.domain;

//import javax.persistence.*;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "nascimento", nullable = false)
    private Date nascimento;

    @ManyToMany(fetch = FetchType.EAGER,
                cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "livro_autor",
            joinColumns = { @JoinColumn(name = "autor_id") },
            inverseJoinColumns = { @JoinColumn(name = "livro_id") })
    private List<Livro> livros;

    public Autor() {
    }

    public Autor(String nome, Date nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public Autor(long id, String nome, Date nascimento) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        StringBuilder autorStr = new StringBuilder("Autor {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nascimento='" + nascimento + '\'');
        if (livros != null) {
            for (Livro livro : livros) {
                autorStr.append(livro.getTitulo()).append(";");
            }
        }
        autorStr.append('}');

        return autorStr.toString();
    }
}