package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.VendedorDAO;
import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class VendedorService {
    @Autowired
    private VendedorDAO vendedorDAO;

    public boolean cadastrar(Vendedor vendedor) {
        try {
            vendedorDAO.cadastrar(new Vendedor(gerarMatricula(), vendedor.getNome()));
            return true;
        } catch (Exception e) {
            vendedorDAO.cadastrar(new Vendedor(String.valueOf(Integer.parseInt(gerarMatricula()) + 100), vendedor.getNome()));
            return false;
        }
    }

    public Vendedor buscar(String matricula) {
        try {
            return vendedorDAO.buscar(matricula);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deletar(String matricula) {
        try {
            vendedorDAO.deletar(matricula);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean alterar(Vendedor vendedor) {
        try {
            if (vendedor.getAtivo() == '1') {
                vendedorDAO.alterar(vendedor);
                return true;
            } else {
                vendedorDAO.alterar(new Vendedor(vendedorDAO.buscar(vendedor.getMatricula()).getAtivo(), vendedor.getMatricula(), vendedor.getNome()));
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public List<Tuple> listarNumeroDeVendas() {
        try {
            return vendedorDAO.listarMaiorNumeroDeVendas();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Tuple> listarPorValorVendido() {
        try {
            return vendedorDAO.listarPorValor();
        } catch (Exception e) {
            return null;
        }
    }

    public String gerarMatricula() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yymm"));
    }

    public String informarMatriculaNovoVendedor(String nome) {
        try {
            return vendedorDAO.pegarMatriculaPorNome(nome);
        } catch (Exception e) {
            return null;
        }
    }
}
