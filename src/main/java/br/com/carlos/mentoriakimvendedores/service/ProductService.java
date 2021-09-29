package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.ProductRepository;
import br.com.carlos.mentoriakimvendedores.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product cadastrar(Product product) {
        return repository.save(product);
    }

    public Product deletar(int id) {
        System.out.println(id);
        Product product = repository.findById(id);
        return repository.save(new Product(product.getId(),
                product.getNome(),
                product.getValor(),
                false));
    }

    public Product alterar(Product product) {
        return repository.save(product);
    }

    public List<Product> listar() {
        return repository.findAll();
    }

    public Map<Integer, String> listarProdutosPorId() {
        Map<Integer, String> produtosPorId = new HashMap<>();
        List<Product> products = repository.findAll();
        for (Product product : products) {
            produtosPorId.put(product.getId(), product.getNome());
        }
        return produtosPorId;
    }
}
