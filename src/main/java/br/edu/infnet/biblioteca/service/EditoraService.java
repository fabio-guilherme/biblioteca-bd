package br.edu.infnet.biblioteca.service;

import br.edu.infnet.biblioteca.model.domain.Editora;

import java.util.List;

public abstract class EditoraService {

    public abstract List<Editora> buscarTodas();

    public abstract Editora buscarPorId(int id);

    public abstract Editora salvar(Editora editora);

    public abstract Editora atualizar(Editora editora);

    public abstract void excluir(Editora editora);
}