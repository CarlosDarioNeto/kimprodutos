package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.List;

@Component
public class VendedorDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public void cadastrar(Vendedor vendedor) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(vendedor);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Vendedor buscar(String matricula) {
        entityManager = entityManagerFactory.createEntityManager();
        Vendedor vendedor = entityManager.find(Vendedor.class, matricula);
        entityManager.close();
        return vendedor;
    }

    public void deletar(String matricula) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Vendedor vendedor = entityManager.find(Vendedor.class, matricula);
        entityManager.merge(new Vendedor('0', vendedor.getMatricula(), vendedor.getNome()));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void alterar(Vendedor vendedor) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(vendedor);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Tuple> listarMaiorNumeroDeVendas() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("select count(*) as vendas, v.nome as nome, v.matricula as matricula from vendedor v inner join venda f where v.matricula = f.matricula_vendedor and v.ativo='1' group by f.matricula_vendedor order by vendas desc;", Tuple.class);
        return query.getResultList();
    }

    public List<Tuple> listarPorValor() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("select sum(valor_total) as total, v.nome as nome, v.matricula as matricula from venda f inner join vendedor v where v.matricula = f.matricula_vendedor and v.ativo='1' group by f.matricula_vendedor order by total desc;", Tuple.class);
        return query.getResultList();
    }

    public String pegarMatriculaPorNome(String nome) {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("select matricula from vendedor where nome = '" + nome + "';");
        String matricula = query.getResultList().get(0).toString();
        entityManager.close();
        return matricula;
    }
}