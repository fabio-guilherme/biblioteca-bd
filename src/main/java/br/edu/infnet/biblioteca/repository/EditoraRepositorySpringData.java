package br.edu.infnet.biblioteca.repository;

import br.edu.infnet.biblioteca.model.domain.Editora;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepositorySpringData extends CrudRepository<Editora, Integer> {

}