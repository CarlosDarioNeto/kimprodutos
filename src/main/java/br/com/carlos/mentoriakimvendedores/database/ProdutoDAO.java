package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entidade.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Component
public class ProdutoDAO {
    @Autowired
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public void cadastrar(Produto produto) {
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deletar(int id) {
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("update produto set ativo = '0' where id= :id ;");
        query.setParameter("id", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public Produto buscar(int id) {
        entityManager = emf.createEntityManager();
        Produto produto = entityManager.find(Produto.class, id);
        entityManager.close();
        return produto;
    }

    public void alterar(Produto produto) {
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Produto> listar() {
        entityManager = emf.createEntityManager();
        Query query = entityManager.createNamedQuery("produto.list", Produto.class);
        List<Produto> produtos = query.getResultList();
        return produtos;
    }
}
