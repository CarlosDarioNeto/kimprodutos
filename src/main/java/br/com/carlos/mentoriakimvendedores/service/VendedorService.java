package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.VendedorRepository;
import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import br.com.carlos.mentoriakimvendedores.segurança.DetalhesVendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class VendedorService implements UserDetailsService {
    @Autowired
    private VendedorRepository repository;

    public Vendedor cadastrar(Vendedor vendedor) {
        return repository.save(new Vendedor(pegarMatricula(), vendedor.getNome(), vendedor.getPassword()));
    }

    public Vendedor buscar(String matricula) {
        return repository.findById(matricula);
    }

    public Vendedor deletar(String matricula) {
        Vendedor vendedor = repository.findById(matricula);
        return repository.save(new Vendedor(false, vendedor.getMatricula(), vendedor.getNome(), vendedor.getPassword(), vendedor.getRoles()));
    }

    public Vendedor alterar(Vendedor vendedor) {
        Vendedor vendedorBancoDados = repository.findById(vendedor.getMatricula());
        if (vendedorBancoDados != null) {
            return repository.save(new Vendedor(vendedor.isAtivo() ? true : vendedorBancoDados.isAtivo(),
                    vendedorBancoDados.getMatricula(),
                    vendedor.getNome(),
                    vendedor.getPassword(),
                    vendedorBancoDados.getRoles()));
        }
        return null;
    }

    public List<Vendedor> listar() {
        return repository.findAll();
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

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        Optional<Vendedor> vendedor = repository.findByMatricula(matricula);
        vendedor.orElseThrow(() -> new UsernameNotFoundException("Not found " + matricula));
        return vendedor.map(DetalhesVendedor::new).get();
    }
}
