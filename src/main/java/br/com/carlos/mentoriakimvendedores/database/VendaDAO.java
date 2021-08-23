package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entidade.TabelaProdutos;
import br.com.carlos.mentoriakimvendedores.entidade.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Component
public class VendaDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public void cadastrarVenda(Venda venda) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(venda);
        entityManager.getTransaction().commit();
    }

    public void deleteVenda(String id) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Venda.class, id));
        entityManager.getTransaction().commit();
    }

    public List<TabelaProdutos> oferecerVenda() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("select TabelaProdutos.id as id, TabelaProdutos.nome as nome, TabelaProdutos.preco as preco, 0+0 as quantidade from produto TabelaProdutos where TabelaProdutos.ativo='1' ;", TabelaProdutos.class);
        List<TabelaProdutos> list = query.getResultList();
        entityManager.close();
        return list;
    }
}
