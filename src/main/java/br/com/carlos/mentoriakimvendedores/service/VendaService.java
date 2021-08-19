package br.com.carlos.mentoriakimvendedores.service;

import br.com.carlos.mentoriakimvendedores.database.VendaDAO;
import br.com.carlos.mentoriakimvendedores.entidade.Item;
import br.com.carlos.mentoriakimvendedores.entidade.Venda;
import br.com.carlos.mentoriakimvendedores.entidade.VendaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {
    @Autowired
    private VendaDAO vendaDAO;

    public boolean cadastrar(VendaDTO vendaDTO) {
        List<Item> itens = removerItensNãoComprados(vendaDTO);
        double valorTotal = calcularValorTotalVenda(itens);
        return vendaDAO.cadastrarVenda(setIds(new Venda(vendaDTO.getMatricula(), itens, valorTotal)));
    }

    public boolean deletar(String id) {
        return vendaDAO.deleteVenda(id);
    }

    public VendaDTO getVendaDto() {
        return new VendaDTO(vendaDAO.oferecerVenda(), "");
    }

    private String gerarIdVenda() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
    }

    private List<Item> gerarIdsItens(List<Item> items, String idVenda) {
        NumberFormat formatoIdItem = new DecimalFormat("0000"); //aqui ou novo método?
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setId(idVenda + formatoIdItem.format(i));
        }
        return items;
    }

    private Venda setIds(Venda venda) {
        String idVenda = gerarIdVenda();
        return new Venda(idVenda, venda.getMatricula_vendedor(), venda.getValor_total(), gerarIdsItens(venda.getItens(), idVenda));
    }

    private List<Item> removerItensNãoComprados(VendaDTO vendaDTO) {
        List<Item> itens = new ArrayList<>();
        vendaDTO.getItens().stream().filter(item -> item.getQuantidade() > 0).forEach(
                item -> itens.add(new Item(item.getId(), item.getQuantidade())));
        return itens;
    }

    private Double calcularValorTotalVenda(List<Item> items) {
        double valorTotal = 0;
        VendaDTO vendaDTO = getVendaDto();
        for (Item item : items) {
            valorTotal += (item.getQuantidade() * vendaDTO.getItens().get(item.getId_produto()-1).getPreco());
        }
        return valorTotal;
    }
}
