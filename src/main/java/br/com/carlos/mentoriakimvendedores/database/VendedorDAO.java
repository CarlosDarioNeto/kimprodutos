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
                "select count(venda.matricula_vendedor) as vendas," +
                        "vendedor.nome as nome, " +
                        "vendedor.matricula as matricula " +
                        "from vendedor left join venda on vendedor.matricula = venda.matricula_vendedor " +
                        "group by vendedor.matricula order by vendas desc;", Tuple.class);
        return query.getResultList();
    }

    public List<Tuple> listarPorValor() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery(
                "select sum(valor_total) as total, " +
                "vendedor.nome as nome, " +
                "vendedor.matricula as matricula " +
                "from venda right join vendedor " +
                "on vendedor.matricula = venda.matricula_vendedor and " +
                "vendedor.ativo='1' " +
                "group by vendedor.matricula " +
                "order by total desc;", Tuple.class);
        return query.getResultList();
    }
}