package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.ProductRepository;
import br.com.carlos.mentoriakimvendedores.database.SaleRepository;
import br.com.carlos.mentoriakimvendedores.database.SalesmanRepository;
import br.com.carlos.mentoriakimvendedores.entity.Item;
import br.com.carlos.mentoriakimvendedores.entity.Product;
import br.com.carlos.mentoriakimvendedores.entity.Sale;
import br.com.carlos.mentoriakimvendedores.entity.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SalesmanRepository salesmanRepository;
    @Autowired
    private SaleRepository repository;
    private final Logger logger = LoggerFactory.getLogger(SaleService.class);

    public Sale cadastrar(Sale sale, String matricula) {
        Salesman salesman = salesmanRepository.findById(matricula);
        sale.setItens(setarValorProdutoCorrente(sale.getItens()));
        sale.setItens(removerItensNaoComprados(sale));
        sale = setIds(sale);
        Double valorototal = calcularValorTotalVenda(sale.getItens());
        logger.info("cadastrar venda {}", sale.getItens().size());
        return repository.save(new Sale(sale.getId(), salesman, valorototal, sale.getItens()));
    }

    public Sale deletar(String id) {
        Sale sale = repository.findById(id);
        return repository.delete(sale);
    }

    public Sale gerarVenda() {
        Sale sale = new Sale();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.isAtivo()) {
                sale.getItens().add(new Item(product.getId(), 0, product.getValor()));
            }
        }
        return sale;
    }

    public Map<Salesman, Integer> listarVendedoresPorQuantidadeVendas() {
        List<Sale> sales = repository.findAll();
        Map<Salesman, Integer> quantidadeVendas = new HashMap<>();
        for (Sale sale : sales) {
            quantidadeVendas.merge(sale.getVendedor(), 1, Integer::sum);
        }
        logger.info("listarVendedoresPorVenda = {}", quantidadeVendas);
        return quantidadeVendas;
    }

    public Map<Salesman, Double> listarVendedoresPorValorVendido() {
        List<Sale> sales = repository.findAll();
        Map<Salesman, Double> valorVendido = new HashMap<>();
        for (Sale sale : sales) {
            if (valorVendido.get(sale.getVendedor()) == null) {
                valorVendido.put(sale.getVendedor(), sale.getValor_total());
            } else {
                valorVendido.put(sale.getVendedor(), valorVendido.get(sale.getVendedor()) + sale.getValor_total());
            }
        }
        logger.info("listarPorValorVendido = {}", valorVendido);
        return valorVendido;
    }


    private List<Item> setarValorProdutoCorrente(List<Item> itens) {
        List<Product> products = productRepository.findAll();
        for (Item item : itens) {
            item.setPreco_corrente(products.get(item.getId_produto() - 1).getValor());
        }
        return itens;
    }

    private List<Item> removerItensNaoComprados(Sale sale) {
        return sale.getItens().stream()
                .filter(item -> item.getQuantidade() > 0)
                .collect(Collectors.toList());
    }

    private Sale setIds(Sale sale) {
        String idVenda = gerarIdVenda();
        return new Sale(idVenda, sale.getVendedor(), sale.getValor_total(), gerarIdsItens(sale.getItens(), idVenda));
    }

    private String gerarIdVenda() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
    }

    private List<Item> gerarIdsItens(List<Item> itens, String idVenda) {
        NumberFormat formatoIdItem = new DecimalFormat("0000");
        for (int i = 0; i < itens.size(); i++) {
            itens.get(i).setId(idVenda + formatoIdItem.format(i));
        }
        return itens;
    }

    private Double calcularValorTotalVenda(List<Item> itens) {
        double valorTotal = 0;
        for (Item item : itens) {
            valorTotal += (item.getQuantidade() * item.getPreco_corrente());
        }
        return valorTotal;
    }
}
