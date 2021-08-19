package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entidade.TabelaProdutos;
import br.com.carlos.mentoriakimvendedores.entidade.Venda;
import br.com.carlos.mentoriakimvendedores.entidade.VendaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class VendaDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Bean
    public void criarEntityManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    public boolean cadastrarVenda(Venda venda) {
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(venda);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    public boolean deleteVenda(String id) {
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (entityManager.find(Venda.class, id) != null) {
                entityManager.remove(entityManager.find(Venda.class, id));
                entityManager.getTransaction().commit();
                return true;
            } else {
                throw new Exception("venda não existe");
            }
        } catch (Exception e) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    public List<TabelaProdutos> oferecerVenda() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("select TabelaProdutos.id as id, TabelaProdutos.nome as nome, TabelaProdutos.preco as preco, 0+0 as quantidade from produto TabelaProdutos;", TabelaProdutos.class);
        List list=query.getResultList();
        entityManager.close();
        return list;
    }
}
