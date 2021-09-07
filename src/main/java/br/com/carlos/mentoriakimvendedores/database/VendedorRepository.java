package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends org.springframework.data.repository.Repository<Vendedor,String> {
    Vendedor save(Vendedor vendedor);
    Vendedor findById(String id);
    Vendedor findByNome(String nome);

}
