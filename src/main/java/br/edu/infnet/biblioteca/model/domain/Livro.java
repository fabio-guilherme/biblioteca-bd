package br.edu.infnet.biblioteca.model.domain;

import javax.persistence.*;

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

    // Getters e setters
}