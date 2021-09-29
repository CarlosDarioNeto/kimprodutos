package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entity.Sale;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface SaleRepository extends Repository<Sale, String> {

    Sale save(Sale sale);

    Sale findById(String id);

    Sale delete(Sale sale);

    List<Sale> findAll();
}
