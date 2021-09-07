package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.ProdutoRepository;
import br.com.carlos.mentoriakimvendedores.entidade.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto cadastrar(Produto produto) {
        return repository.save(produto);
    }

    public Produto deletar(int id) {
        Produto produto = repository.findById(id);
        return repository.save(new Produto(produto.getId(),
                produto.getNome(),
                produto.getValor(),
                false));
    }

    public Produto alterar(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscar(int id) {
        return repository.findById(id);
    }
}
