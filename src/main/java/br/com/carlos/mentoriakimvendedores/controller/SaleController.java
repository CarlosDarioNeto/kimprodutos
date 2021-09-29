package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.entity.Sale;
import br.com.carlos.mentoriakimvendedores.service.ProductService;
import br.com.carlos.mentoriakimvendedores.service.SaleService;
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
public class SaleController {

    @Autowired
    private SaleService saleService;
    @Autowired
    private ProductService productService;
    private final static Logger logger = LoggerFactory.getLogger(SaleController.class);

    @GetMapping("sale")
    public ModelAndView showVenda() {
        ModelAndView modelAndView=new ModelAndView("sale");
        modelAndView.addObject("nome","null");
        return modelAndView;
    }

    @GetMapping("delete_sale")
    public ModelAndView deletarVenda(@RequestParam(name = "codigoVenda") String codigoVenda) {
        saleService.deletar(codigoVenda);
        return new ModelAndView("sale");
    }

    @GetMapping("selecionar_produtos_venda")
    public ModelAndView mostrarProdutosVenda() {
        Sale sale = saleService.gerarVenda();
        Map<Integer, String> map_produtos = productService.listarProdutosPorId();
        ModelAndView modelAndView = new ModelAndView("tabelavenda");
        modelAndView.addObject("sale", sale);
        modelAndView.addObject("map_produtos", map_produtos);
        logger.info("Map Produtos {}", map_produtos.get(1));
        return modelAndView;
    }

    @GetMapping("cadastrar_venda")
    public ModelAndView cadastrarVenda(@ModelAttribute(name = "sale") Sale sale,
                                       @RequestParam(name = "matricula")String matricula) {
        logger.info("Cadastrar Venda: {}", sale);
        saleService.cadastrar(sale,matricula);
        return new ModelAndView("sale");
    }
}
