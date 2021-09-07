package br.com.carlos.mentoriakimvendedores.database;

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

    public List<Tuple> listarMaiorNumeroDeVendas() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery(
                "select count(*) as vendas," +
                        " v.nome as nome, " +
                        "v.matricula as matricula " +
                        "from vendedor v inner join venda f where v.matricula = f.matricula_vendedor " +
                        "group by f.matricula_vendedor order by vendas desc;", Tuple.class);
        return query.getResultList();
    }

    public List<Tuple> listarPorValor() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("select sum(valor_total) as total, v.nome as nome, v.matricula as matricula from venda f inner join vendedor v where v.matricula = f.matricula_vendedor and v.ativo='1' group by f.matricula_vendedor order by total desc;", Tuple.class);
        return query.getResultList();
    }
}