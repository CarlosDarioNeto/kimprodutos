package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.entidade.Produto;
import br.com.carlos.mentoriakimvendedores.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller()
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("produto")
    public ModelAndView showProduto() {
        return new ModelAndView("produto");
    }

    @GetMapping("cadProduto")
    public ModelAndView cadastrarProduto(@RequestParam(name = "nome") String nome,
                                         @RequestParam(name = "valor") String valor) {
        produtoService.cadastrar(new Produto(nome, Double.parseDouble(valor)));
        return new ModelAndView("produto");
    }

    @GetMapping("altProduto")
    public ModelAndView alterarProduto(@RequestParam(name = "nome") String nome,
                                       @RequestParam(name = "valor") String valor,
                                       @RequestParam(name = "id") String id,
                                       @RequestParam(name = "ativar", defaultValue = "0") boolean ativo) {
        produtoService.alterar(new Produto(Integer.parseInt(id), nome, Double.parseDouble(valor), ativo));
        return new ModelAndView("produto");
    }

    @GetMapping("delproduto")
    public ModelAndView deletarProduto(@RequestParam(name = "id") int id) {
        produtoService.deletar(id);
        return new ModelAndView("produto");
    }

    @GetMapping("listarProduto")
    public ModelAndView listarProdutos() {
        List<Produto> produtos = produtoService.listar();
        ModelAndView modelAndView = new ModelAndView("tabelaprodutos");
        modelAndView.addObject("produtos", produtos);
        return modelAndView;
    }
}
