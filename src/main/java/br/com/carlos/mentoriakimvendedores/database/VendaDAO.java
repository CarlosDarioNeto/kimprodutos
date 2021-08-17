package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entidade.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
}
