package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.entidade.Produto;
import br.com.carlos.mentoriakimvendedores.entidade.Venda;
import br.com.carlos.mentoriakimvendedores.service.ProdutoService;
import br.com.carlos.mentoriakimvendedores.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VendaController {

    @Autowired
    private VendaService vendaService;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("venda")
    public ModelAndView showVenda() {
        return new ModelAndView("venda");
    }

    @GetMapping("delVenda")
    public ModelAndView deletarVenda(@RequestParam(name = "codigoVenda") String codigoVenda) {
        vendaService.deletar(codigoVenda);
        return new ModelAndView("venda");
    }

    @GetMapping("listarProdutosVenda")
    public ModelAndView mostrarProdutosVenda() {
        //VendaDTO vendaDTO = vendaService.getVendaDto();

        Venda venda= vendaService.gerarVenda();
        List<Produto> produtos = produtoService.listar();
/*
        venda.getItens().get().getQuantidade()
        venda.getItens().get().getPreco_corrente()
        venda.getItens().get().getId_produto()
*/
        ModelAndView modelAndView = new ModelAndView("tabelavenda");
        modelAndView.addObject("venda", venda);
        modelAndView.addObject("product", produtos);
        return modelAndView;
    }

    @GetMapping("cadVenda")
    public ModelAndView cadastrarVenda() {

        return new ModelAndView("venda");
    }
}
