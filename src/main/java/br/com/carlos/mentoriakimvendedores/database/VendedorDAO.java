package br.com.carlos.mentoriakimvendedores.database;

import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public void criarEntityManager3() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    public boolean cadastrar(Vendedor vendedor) {
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(vendedor);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    public Vendedor buscar(String matricula) {
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            return entityManager.find(Vendedor.class, matricula);
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public boolean deletar(String matricula) {
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (entityManager.find(Vendedor.class, matricula) != null) {
                entityManager.remove(entityManager.find(Vendedor.class, matricula));
                entityManager.getTransaction().commit();
                return true;
            } else {
                throw new Exception("Vendedor não existe");
            }
        } catch (Exception e) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    public boolean alterar(Vendedor vendedor) {
        entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            if(entityManager.find(Vendedor.class,vendedor.getMatricula()) != null){
                entityManager.merge(vendedor);
                entityManager.getTransaction().commit();
                return true;
            }else{
                throw new Exception("Vendedor inexistente");
            }
        }catch (Exception e){
            return false;
        }finally {
            entityManager.close();
        }
    }

    public List<Tuple> listarMaiorNumeroDeVendas() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("select count(*) as vendas, v.nome as nome, v.matricula as matricula from vendedor v inner join venda f where v.matricula = f.matricula_vendedor group by f.matricula_vendedor order by vendas desc;", Tuple.class);
        return query.getResultList();
    }

    public List<Tuple> listarPorValor() {
        Query query = entityManager.createNativeQuery("select sum(valor_total) as total, v.nome as nome, v.matricula as matricula from venda f inner join vendedor v where v.matricula = f.matricula_vendedor group by f.matricula_vendedor order by total desc;", Tuple.class);
        return query.getResultList();
    }
}




 /* for(Tuple rt : t ){
            System.out.println(rt.get("vendas"));
            System.out.println(rt.get("vendedor"));
        }*/