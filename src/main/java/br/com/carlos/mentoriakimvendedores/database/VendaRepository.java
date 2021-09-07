package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entidade.Venda;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface VendaRepository extends Repository<Venda,String> {
    Venda save(Venda venda);
    Venda findById(String id);
    Venda delete(Venda venda);
    List<Venda> findAll();
}
