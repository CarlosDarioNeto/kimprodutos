package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.entity.Salesman;
import br.com.carlos.mentoriakimvendedores.service.SaleService;
import br.com.carlos.mentoriakimvendedores.service.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class SalesmanController {
    @Autowired
    private SalesmanService salesmanService;
    @Autowired
    private SaleService saleService;

    @GetMapping("salesman")
    public ModelAndView showVendedor() {
        ModelAndView modelAndView = new ModelAndView("salesman");
        modelAndView.addObject("nome", salesmanService.getLoggedUsername());
        modelAndView.addObject("salesman", new Salesman("1", "1", "1"));
        modelAndView.addObject("NovoVendedor", new Salesman("1", "1", "1"));
        modelAndView.addObject("modalVendedor", true);
        return modelAndView;
    }

    @GetMapping("insert_salesman")
    public ModelAndView cadastrarVendedor(@RequestParam(name = "nome") String nome, @RequestParam(name = "senha") String senha) {
        salesmanService.cadastrar(new Salesman("", nome, senha));
        ModelAndView modelAndView = new ModelAndView("salesman");
        modelAndView.addObject("salesman", new Salesman("1", "1", "1"));
        modelAndView.addObject("modalVendedor", false);
        modelAndView.addObject("NovoVendedor", new Salesman(salesmanService.informarMatriculaNovoVendedor(nome), nome, senha));
        return modelAndView;
    }

    @GetMapping("update_salesman")
    public ModelAndView alterarVendedor(@RequestParam(name = "nome") String nome,
                                        @RequestParam(name = "matricula") String matricula,
                                        @RequestParam(name = "senha") String senha,
                                        @RequestParam(name = "ativar", defaultValue = "0") boolean ativo) {
        salesmanService.alterar(new Salesman(ativo, matricula, nome, senha, " "));
        return showVendedor();
    }

    @GetMapping("disable_salesman")
    public ModelAndView deletarVendedor(@RequestParam(name = "matricula") String matricula) {
        salesmanService.deletar(matricula);
        return showVendedor();
    }

    @GetMapping("search_salesman")
    public ModelAndView buscarVendedor(@RequestParam(name = "matricula") String matricula) {
        Salesman salesman = salesmanService.buscar(matricula);
        ModelAndView modelAndView = new ModelAndView("salesman");
        if (salesman != null) {
            modelAndView.addObject("salesman", salesman);
            modelAndView.addObject("ativo", salesman.isAtivo() ? "SIM" : "NÃO");
        } else {
            modelAndView.addObject("salesman", new Salesman("2", "1", "1"));
            modelAndView.addObject("ativo", "-");
        }
        modelAndView.addObject("modalVendedor", true);
        modelAndView.addObject("NovoVendedor", new Salesman("1", "1", "1"));
        return modelAndView;
    }

    @GetMapping("list_saleman_by_sales")
    public ModelAndView listarVendedorVendas() {
        Map<Salesman, Integer> vendedoresVendas = saleService.listarVendedoresPorQuantidadeVendas();
        List<Salesman> vendedores = salesmanService.listar();
        ModelAndView modelAndView = new ModelAndView("salesman");
        modelAndView.addObject("nome", salesmanService.getLoggedUsername());
        modelAndView.addObject("vendedoresVendas", vendedoresVendas);
        modelAndView.addObject("vendedores", vendedores);
        modelAndView.addObject("salesman", new Salesman("1", "1", "1"));
        modelAndView.addObject("modalVendedor", true);
        modelAndView.addObject("NovoVendedor", new Salesman("1", "1", "1"));
        return modelAndView;
    }

    @GetMapping("list_saleman_by_value")
    public ModelAndView listarVendedorValor() {
        Map<Salesman, Double> vendedoresValor = saleService.listarVendedoresPorValorVendido();
        List<Salesman> vendedores = salesmanService.listar();
        ModelAndView modelAndView = new ModelAndView("salesman");
        modelAndView.addObject("nome", salesmanService.getLoggedUsername());
        modelAndView.addObject("vendedoresValor", vendedoresValor);
        modelAndView.addObject("vendedorees", vendedores);
        modelAndView.addObject("salesman", new Salesman("1", "1", "1"));
        modelAndView.addObject("modalVendedor", true);
        modelAndView.addObject("NovoVendedor", new Salesman("1", "1", "1"));
        return modelAndView;
    }
}
