package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.ProdutoDAO;
import br.com.carlos.mentoriakimvendedores.entidade.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoDAO produtoDAO;

    public boolean cadastrar(Produto produto) {
        try{
            produtoDAO.cadastrar(new Produto(produto.getId(), produto.getNome(), produto.getValor(), '1'));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deletar(int id) {
        try{
            produtoDAO.deletar(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean alterar(Produto produto) {
        try{
            if(produto.getAtivo()=='1'){
                 produtoDAO.alterar(produto);
            }else{
                 produtoDAO.alterar(new Produto(produto.getId(),produto.getNome(),produto.getValor(),produtoDAO.buscar(produto.getId()).getAtivo()));
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Produto> listar() {
        try{
            return produtoDAO.listar();
        }catch (Exception e){
            return null;
        }
    }
}
