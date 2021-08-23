package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.VendaDAO;
import br.com.carlos.mentoriakimvendedores.database.VendedorDAO;
import br.com.carlos.mentoriakimvendedores.entidade.Item;
import br.com.carlos.mentoriakimvendedores.entidade.Venda;
import br.com.carlos.mentoriakimvendedores.entidade.VendaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {
    @Autowired
    private VendaDAO vendaDAO;
    @Autowired
    private VendedorDAO vendedorDAO;

    public boolean cadastrar(VendaDTO vendaDTO) {
        try {
            List<Item> itens = removerItensNãoComprados(vendaDTO);
            setarValorProdutoCorrente(itens);
            double valorTotal = calcularValorTotalVenda(itens);
            vendaDAO.cadastrarVenda(setIds(new Venda(vendedorDAO.buscar(vendaDTO.getMatricula()), valorTotal, itens)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deletar(String id) {
        try {
            vendaDAO.deleteVenda(id);
            return false;
        } catch (Exception e) {
            return false;
        }
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

    private List<Item> removerItensNãoComprados(VendaDTO vendaDTO) {
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
            System.out.println(item.getId_produto());
        }
        return itens;
    }
}
