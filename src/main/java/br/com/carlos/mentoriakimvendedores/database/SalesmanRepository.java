package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entity.Salesman;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesmanRepository extends org.springframework.data.repository.Repository<Salesman, String>, JpaSpecificationExecutor<Salesman> {
    Salesman save(Salesman salesman);

    Salesman findById(String id);

    Salesman findByNome(String nome);

    List<Salesman> findAll();

    Optional<Salesman> findByMatricula(String userName);
}
