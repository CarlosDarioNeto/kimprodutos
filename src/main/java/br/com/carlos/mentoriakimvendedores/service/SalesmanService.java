package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.SalesmanRepository;
import br.com.carlos.mentoriakimvendedores.entity.Salesman;
import br.com.carlos.mentoriakimvendedores.security.SalesmanDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class SalesmanService implements UserDetailsService {
    @Autowired
    private SalesmanRepository repository;

    public Salesman cadastrar(Salesman salesman) {
        return repository.save(new Salesman(pegarMatricula(), salesman.getNome(), encryptingPassword(salesman.getPassword())));
    }

    public Salesman buscar(String matricula) {
        return repository.findById(matricula);
    }

    public Salesman deletar(String matricula) {
        Salesman salesman = repository.findById(matricula);
        return repository.save(new Salesman(false, salesman.getMatricula(), salesman.getNome(), encryptingPassword(salesman.getPassword()), salesman.getRoles()));
    }

    public Salesman alterar(Salesman salesman) {
        Salesman salesmanBancoDados = repository.findById(salesman.getMatricula());
        if (salesmanBancoDados != null) {
            return repository.save(new Salesman(salesman.isAtivo() ? true : salesmanBancoDados.isAtivo(),
                    salesmanBancoDados.getMatricula(),
                    salesman.getNome(),
                    encryptingPassword(salesman.getPassword()),
                    salesmanBancoDados.getRoles()));
        }
        return null;
    }

    public List<Salesman> listar() {
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
        Optional<Salesman> vendedor = repository.findByMatricula(matricula);
        vendedor.orElseThrow(() -> new UsernameNotFoundException("Not found " + matricula));
        return vendedor.map(SalesmanDetails::new).get();
    }

    private String encryptingPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    public String getLoggedUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
