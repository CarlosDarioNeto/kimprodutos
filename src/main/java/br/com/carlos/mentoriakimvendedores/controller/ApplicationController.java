package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.entidade.Produto;
import br.com.carlos.mentoriakimvendedores.entidade.Venda;
import br.com.carlos.mentoriakimvendedores.entidade.VendaDTO;
import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import br.com.carlos.mentoriakimvendedores.service.ProdutoService;
import br.com.carlos.mentoriakimvendedores.service.VendaService;
import br.com.carlos.mentoriakimvendedores.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Tuple;
import javax.swing.*;
import java.util.List;

@Controller("/")
public class ApplicationController {

    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private VendaService vendaService;

    @GetMapping()
    public ModelAndView showHome() {
        return new ModelAndView("index");
    }

    /******************************************* VENDEDOR ******************************/

    @GetMapping("vendedor")
    public ModelAndView showVendedor() {
        ModelAndView modelAndView = new ModelAndView("vendedor");
        modelAndView.addObject("vendedor", new Vendedor("1", "1"));
        modelAndView.addObject("NovoVendedor", new Vendedor("1", "1"));
        modelAndView.addObject("modalVendedor",true);
        return modelAndView;
    }

    @GetMapping("cadvendedor")
    public ModelAndView cadastrarVendedor(@RequestParam(name = "nome") String nome) {
        vendedorService.cadastrar(new Vendedor("", nome));
        ModelAndView modelAndView = new ModelAndView("vendedor");
        modelAndView.addObject("vendedor", new Vendedor("1", "1"));
        modelAndView.addObject("modalVendedor",false);
        modelAndView.addObject("NovoVendedor",new Vendedor(vendedorService.informarMatriculaNovoVendedor(nome),nome));
        return modelAndView;
    }

    @GetMapping("altvendedor")
    public ModelAndView alterarVendedor(@RequestParam(name = "nome") String nome,
                                        @RequestParam(name = "matricula") String matricula) {
        vendedorService.alterar(new Vendedor(matricula, nome));
        ModelAndView modelAndView = new ModelAndView("vendedor");
        modelAndView.addObject("vendedor", new Vendedor("1", "1"));
        modelAndView.addObject("modalVendedor",true);
        modelAndView.addObject("NovoVendedor", new Vendedor("1", "1"));
        return modelAndView;
    }

    @GetMapping("delvendedor")
    public ModelAndView deletarVendedor(@RequestParam(name = "matricula") String matricula) {
        vendedorService.deletar(matricula);
        ModelAndView modelAndView = new ModelAndView("vendedor");
        modelAndView.addObject("vendedor", new Vendedor("1", "1"));
        modelAndView.addObject("modalVendedor",true);
        modelAndView.addObject("NovoVendedor", new Vendedor("1", "1"));
        return modelAndView;
    }

    @GetMapping("busvendedor")
    public ModelAndView buscarVendedor(@RequestParam(name = "matricula") String matricula) {
        Vendedor vendedor = vendedorService.buscar(matricula);
        ModelAndView modelAndView = new ModelAndView("vendedor");
        if (vendedor != null) {
            modelAndView.addObject("vendedor", vendedor);
        } else {
            modelAndView.addObject("vendedor", new Vendedor("2", "1"));
        }
        modelAndView.addObject("modalVendedor",true);
        modelAndView.addObject("NovoVendedor", new Vendedor("1", "1"));
        return modelAndView;
    }

    @GetMapping("listarVendedorVendas")
    public ModelAndView listarVendedorVendas() {
        List<Tuple> vendedoresVendas = vendedorService.listarNumeroDeVendas();
        ModelAndView modelAndView = new ModelAndView("vendedor");
        modelAndView.addObject("vendedoresVendas", vendedoresVendas);
        modelAndView.addObject("vendedor", new Vendedor("1", "1"));
        modelAndView.addObject("modalVendedor",true);
        modelAndView.addObject("NovoVendedor", new Vendedor("1", "1"));
        return modelAndView;
    }

    @GetMapping("listarVendedorValor")
    public ModelAndView listarVendedorValor() {
        List<Tuple> vendedoresValor = vendedorService.listarPorValorVendido();
        ModelAndView modelAndView = new ModelAndView("vendedor");
        modelAndView.addObject("vendedoresValor", vendedoresValor);
        modelAndView.addObject("vendedor", new Vendedor("1", "1"));
        modelAndView.addObject("modalVendedor",true);
        modelAndView.addObject("NovoVendedor", new Vendedor("1", "1"));
        return modelAndView;
    }

    /**********************************************************************************/
    /*******************************  PRODUTO  ****************************************/

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
                                       @RequestParam(name = "id") String id) {
        produtoService.alterar(new Produto(Integer.parseInt(id), nome, Double.parseDouble(valor)));
        return new ModelAndView("produto");
    }

    @GetMapping("delproduto")
    public ModelAndView deletarProduto(@RequestParam(name = "id") int id) {
        produtoService.deletar(id);
        return new ModelAndView("produto");
    }

    @GetMapping("listarProduto")
    public ModelAndView listarProdutos() {
        List<Tuple> produtos = produtoService.listar();
        ModelAndView modelAndView = new ModelAndView("tabelaprodutos");
        modelAndView.addObject("produtos", produtos);
        return modelAndView;
    }

    /*****************************************************************************************/

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
        VendaDTO vendaDTO = vendaService.getVendaDto();


        ModelAndView modelAndView = new ModelAndView("tabelavenda");
        modelAndView.addObject("venda", vendaDTO);
        return modelAndView;
    }

    @GetMapping("cadVenda")
    public ModelAndView cadastrarVenda(@ModelAttribute VendaDTO venda) {
        vendaService.cadastrar(venda);
        return new ModelAndView("venda");
    }
}