/*
package br.edu.infnet.biblioteca.service;

import br.edu.infnet.biblioteca.model.domain.Editora;
import br.edu.infnet.biblioteca.repository.EditoraRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraServiceSpringData {

    @Autowired
    private EditoraRepositorySpringData editoraRepository;

    public List<Editora> listarTodas() {
        return editoraRepository.findAll();
    }

    public Editora listarPorId(Integer id) {
        return editoraRepository.findById(id).orElseThrow();
    }

    public List<Editora> listarPorCidade(String cidade) {
        return editoraRepository.findByCidade(cidade);
    }

    public Editora salvar(Editora editora) {
        return editoraRepository.save(editora);
    }

    public void atualizar(Editora editora) {
        editoraRepository.save(editora);
    }

    public void excluir(Editora editora) {
        editoraRepository.delete(editora);
    }
}
*/