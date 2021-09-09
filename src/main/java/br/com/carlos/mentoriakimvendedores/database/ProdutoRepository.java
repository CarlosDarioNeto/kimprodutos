package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entidade.Produto;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ProdutoRepository extends Repository<Produto, Integer> {

    Produto findById(int id);

    List<Produto> findAll();

    Produto save(Produto produto);
}
