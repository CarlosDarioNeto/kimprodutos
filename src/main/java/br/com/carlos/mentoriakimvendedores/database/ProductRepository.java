package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entity.Product;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ProductRepository extends Repository<Product, Integer> {

    Product findById(int id);

    List<Product> findAll();

    Product save(Product product);
}
