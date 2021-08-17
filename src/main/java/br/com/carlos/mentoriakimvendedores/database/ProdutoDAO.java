package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entidade.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProdutoDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Bean
    public void criarEntityManager2() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    public boolean cadastrar(Produto produto) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Query query= entityManager.createNativeQuery("insert into produto (nome,preco) values ('"+produto.getNome()+"',"+produto.getValor()+");");
            query.executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deletar(int id) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            if (entityManager.find(Produto.class, id) != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(entityManager.find(Produto.class, id));
                entityManager.getTransaction().commit();
                return true;
            } else {
                throw new Exception("produto não existe");
            }
        } catch (Exception e) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    public boolean alterar(Produto produto) {
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(produto);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }finally {
            entityManager.close();
        }
    }

    public List<Tuple> listar() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("select id as id, nome as nome, preco as valor from produto order by id asc", Tuple.class);
        return query.getResultList();
    }
}
