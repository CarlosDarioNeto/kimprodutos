package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.ProdutoDAO;
import br.com.carlos.mentoriakimvendedores.entidade.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
@Service
public class ProdutoService {
    @Autowired
    private ProdutoDAO produtoDAO;

    public boolean cadastrar(Produto produto) {
        return produtoDAO.cadastrar(produto);
    }

    public boolean deletar(int id) {
        return produtoDAO.deletar(id);
    }

    public boolean alterar(Produto produto) {
        return produtoDAO.alterar(produto);
    }

    public List<Tuple> listar() {
        return produtoDAO.listar();
    }
}
