package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends org.springframework.data.repository.Repository<Vendedor, String>, JpaSpecificationExecutor<Vendedor> {
    Vendedor save(Vendedor vendedor);

    Vendedor findById(String id);

    Vendedor findByNome(String nome);

    List<Vendedor> findAll();
}
