package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.VendedorDAO;
import br.com.carlos.mentoriakimvendedores.database.VendedorRepository;
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
    @Autowired
    private VendedorRepository repository;

    public Vendedor cadastrar(Vendedor vendedor) {
        return repository.save(new Vendedor(pegarMatricula(), vendedor.getNome()));
    }

    public Vendedor buscar(String matricula) {
        return repository.findById(matricula);
    }

    public Vendedor deletar(String matricula) {
        Vendedor vendedor = repository.findById(matricula);
        return repository.save(new Vendedor(false, vendedor.getMatricula(), vendedor.getNome()));
    }

    public Vendedor alterar(Vendedor vendedor) {
        return repository.save(vendedor);
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

    private String gerarMatricula() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yymm"));
    }

    private String pegarMatricula() {
        int matricula = Integer.parseInt(gerarMatricula());
        while (repository.findById(String.valueOf(matricula)) != null) {
            matricula += 100;
        }
        return String.valueOf(matricula);
    }

    public String informarMatriculaNovoVendedor(String nome) {
        try {
            return (repository.findByNome(nome)).getMatricula();
        } catch (Exception e) {
            return null;
        }
    }
}
