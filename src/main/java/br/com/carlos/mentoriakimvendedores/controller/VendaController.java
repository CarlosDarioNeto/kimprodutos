package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.entidade.Item;
import br.com.carlos.mentoriakimvendedores.entidade.Venda;
import br.com.carlos.mentoriakimvendedores.service.ProdutoService;
import br.com.carlos.mentoriakimvendedores.service.VendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class VendaController {

    @Autowired
    private VendaService vendaService;
    @Autowired
    private ProdutoService produtoService;
    private final static Logger logger = LoggerFactory.getLogger(VendaController.class);

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
        Venda venda = vendaService.gerarVenda();
        Map<Integer, String> map_produtos = produtoService.listarProdutosPorId();
        ModelAndView modelAndView = new ModelAndView("tabelavenda");
        modelAndView.addObject("venda", venda);
        modelAndView.addObject("map_produtos", map_produtos);
        logger.info("Map Produtos {}",map_produtos.get(1));
        map_produtos.get(venda.getItens().get(0).getId_produto());
        return modelAndView;
    }

    @GetMapping("cadVenda")
    public ModelAndView cadastrarVenda(@ModelAttribute(name = "venda") Venda venda,
                                       @RequestParam(name = "matricula")String matricula) {
        logger.info("Cadastrar Venda: {}",venda);
        vendaService.cadastrar(venda,matricula);
        return new ModelAndView("venda");
    }
}
