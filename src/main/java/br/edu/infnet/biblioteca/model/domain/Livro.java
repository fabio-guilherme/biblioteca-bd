package br.edu.infnet.biblioteca.model.domain;

//import javax.persistence.*;   // Versões mais antigas

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "ano")
    private int ano;

    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;

    @ManyToMany(mappedBy = "livros",
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private List<Autor> autores;

    public Livro() {
    }
    public Livro(String titulo, int ano) {
        this.titulo = titulo;
        this.ano = ano;
    }

    public Livro(int id, String titulo, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
    @Override
    public String toString() {
        StringBuilder autorStr = new StringBuilder("Autor {" +
                "id=" + id +
                ", título='" + titulo + '\'' +
                ", ano='" + ano + '\'');
        if (autores != null) {
            for (Autor autor : autores) {
                autorStr.append(autor.getNome()).append(";");
            }
        }
        autorStr.append('}');

        return autorStr.toString();
    }
}