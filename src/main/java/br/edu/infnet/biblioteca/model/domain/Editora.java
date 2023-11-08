package br.edu.infnet.biblioteca.model.domain;

//import javax.persistence.*;   // Versões mais antigas

import jakarta.persistence.*;

@Entity
@Table(name = "editora")
public class Editora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cidade")
    private String cidade;

    public Editora() {
        // Empty constructor
    }

    public Editora(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }

    public Editora(int id, String nome, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    @Override
    public String toString() {
        StringBuilder editoraStr = new StringBuilder("Editora {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'');
        editoraStr.append('}');

        return editoraStr.toString();
    }
}