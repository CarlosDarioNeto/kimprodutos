package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.VendedorDAO;
import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;

@Service
public class VendedorService {
    @Autowired
    private VendedorDAO vendedorDAO;

    public boolean cadastrar(Vendedor vendedor){
        return vendedorDAO.cadastrar(vendedor);
    }

    public Vendedor buscar(String matricula){
        return vendedorDAO.buscar(matricula);
    }

    public boolean deletar(String matricula){
        return vendedorDAO.deletar(matricula);
    }

    public boolean alterar(Vendedor vendedor){
        return vendedorDAO.alterar(vendedor);
    }

    public List<Tuple> listarNumeroDeVendas(){
        return vendedorDAO.listarMaiorNumeroDeVendas();
    }

    public List<Tuple> listarPorValorVendido(){
        return vendedorDAO.listarPorValor();
    }
}
