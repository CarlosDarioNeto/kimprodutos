package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.*;
import br.com.carlos.mentoriakimvendedores.entidade.Item;
import br.com.carlos.mentoriakimvendedores.entidade.Venda;
import br.com.carlos.mentoriakimvendedores.entidade.VendaDTO;
import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VendaService {
    @Autowired
    private VendaDAO vendaDAO;
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private VendaRepository repository;

    public Venda cadastrar(VendaDTO vendaDTO) {
        List<Item> itens = removerItensNaoComprados(vendaDTO);
        setarValorProdutoCorrente(itens);
        double valorTotal = calcularValorTotalVenda(itens);
        return repository.save(setIds((new Venda(vendedorRepository.findById(vendaDTO.getMatricula()), valorTotal, itens))));
    }

    public Venda deletar(String id) {
        Venda venda = repository.findById(id);
        return repository.delete(venda);
    }

    public VendaDTO getVendaDto() {
        try {
            return new VendaDTO(vendaDAO.oferecerVenda(), "");
        } catch (Exception e) {
            return null;
        }
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

    private List<Item> removerItensNaoComprados(VendaDTO vendaDTO) {
        return vendaDTO.getItens().stream()
                .filter(item -> item.getQuantidade() > 0)
                .map(item -> new Item(item.getId(), item.getQuantidade()))
                .collect(Collectors.toList());
    }

    private Double calcularValorTotalVenda(List<Item> itens) {
        double valorTotal = 0;
        for (Item item : itens) {
            valorTotal += (item.getQuantidade() * item.getPreco_corrente());
        }
        return valorTotal;
    }

    private List<Item> setarValorProdutoCorrente(List<Item> itens) {
        VendaDTO vendaDTO = getVendaDto();
        for (Item item : itens) {
            item.setPreco_corrente(vendaDTO.getItens().get(item.getId_produto() - 1).getPreco());
        }
        return itens;
    }
}
