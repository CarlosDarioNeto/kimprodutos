package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.controller.ApplicationController;
import br.com.carlos.mentoriakimvendedores.database.*;
import br.com.carlos.mentoriakimvendedores.entidade.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VendaService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private VendaRepository repository;
    private final Logger logger = LoggerFactory.getLogger(VendaService.class);

    public Venda cadastrar() {



        return null;
    }

    public Venda deletar(String id) {
        Venda venda = repository.findById(id);
        return repository.delete(venda);
    }

    public Venda gerarVenda(){
        Venda venda = new Venda();
        List<Produto> produtos = produtoRepository.findAll();

        for(Produto produto : produtos){
            venda.getItens().add(new Item(produto.getId(),0,produto.getValor()));
        }
        return venda;
    }

    public Map<Vendedor,Integer> listarVendedoresPorQuantidadeVendas() {
        List<Venda> vendas = repository.findAll();
        Map<Vendedor,Integer> quantidadeVendas = new HashMap<>();

        for(Venda venda : vendas){
            quantidadeVendas.merge(venda.getVendedor(), 1, Integer::sum);
        }
        logger.info("listarVendedoresPorVenda = {}",quantidadeVendas);
        return quantidadeVendas;
    }

    public Map<Vendedor,Double> listarVendedoresPorValorVendido(){
        List<Venda> vendas = repository.findAll();
        Map<Vendedor,Double> valorVendido = new HashMap<>();
        for (Venda venda : vendas){
            if(valorVendido.get(venda.getVendedor()) == null){
                valorVendido.put(venda.getVendedor(),venda.getValor_total());
            }else{
                valorVendido.put(venda.getVendedor(),valorVendido.get(venda.getVendedor())+venda.getValor_total());
            }
        }
        logger.info("listarPorValorVendido = {}",valorVendido);
        return valorVendido;
    }

    private String gerarIdVenda() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
    }

    private List<Item> gerarIdsItens(List<Item> itens, String idVenda) {
        NumberFormat formatoIdItem = new DecimalFormat("0000"); //aqui ou novo método?
        for (int i = 0; i < itens.size(); i++) {
            itens.get(i).setId(idVenda + formatoIdItem.format(i));
        }
        return itens;
    }

    private Venda setIds(Venda venda) {
        String idVenda = gerarIdVenda();
        return new Venda(idVenda, venda.getVendedor(), venda.getValor_total(), gerarIdsItens(venda.getItens(), idVenda));
    }
/*
    private List<Item> removerItensNaoComprados(VendaDTO vendaDTO) {
        return vendaDTO.getItens().stream()
                .filter(item -> item.getQuantidade() > 0)
                .map(item -> new Item(item.getId(), item.getQuantidade()))
                .collect(Collectors.toList());
    }*/

    private Double calcularValorTotalVenda(List<Item> itens) {
        double valorTotal = 0;
        for (Item item : itens) {
            valorTotal += (item.getQuantidade() * item.getPreco_corrente());
        }
        return valorTotal;
    }

   /* private List<Item> setarValorProdutoCorrente(List<Item> itens) {
        VendaDTO vendaDTO = getVendaDto();
        for (Item item : itens) {
            item.setPreco_corrente(vendaDTO.getItens().get(item.getId_produto() - 1).getPreco());
        }
        return itens;
    }*/
}
