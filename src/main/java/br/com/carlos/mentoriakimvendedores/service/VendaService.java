package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.VendaDAO;
import br.com.carlos.mentoriakimvendedores.entidade.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {
    @Autowired
    private VendaDAO vendaDAO;

    public boolean cadastrar(Venda venda){
        return vendaDAO.cadastrarVenda(venda);
    }

    public boolean deletar(String id){
        return vendaDAO.deleteVenda(id);
    }
}
