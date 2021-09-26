package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import br.com.carlos.mentoriakimvendedores.service.VendaService;
import br.com.carlos.mentoriakimvendedores.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller()
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private VendaService vendaService;

    @GetMapping("/vendedor")
    public ModelAndView showVendedor() {
        ModelAndView modelAndView = new ModelAndView("vendedor");
        modelAndView.addObject("vendedor", new Vendedor("1", "1","1"));
        modelAndView.addObject("NovoVendedor", new Vendedor("1", "1","1"));
        modelAndView.addObject("modalVendedor",true);
        return modelAndView;
    }

    @GetMapping("cadvendedor")
    public ModelAndView cadastrarVendedor(@RequestParam(name = "nome") String nome, @RequestParam(name = "senha")String senha) {
        vendedorService.cadastrar(new Vendedor("", nome,senha));
        ModelAndView modelAndView = new ModelAndView("vendedor");
        modelAndView.addObject("vendedor", new Vendedor("1", "1","1"));
        modelAndView.addObject("modalVendedor",false);
        modelAndView.addObject("NovoVendedor",new Vendedor(vendedorService.informarMatriculaNovoVendedor(nome),nome,senha));
        return modelAndView;
    }

    @GetMapping("altvendedor")
    public ModelAndView alterarVendedor(@RequestParam(name = "nome") String nome,
                                        @RequestParam(name = "matricula") String matricula,
                                        @RequestParam(name = "senha") String senha,
                                        @RequestParam(name = "ativar", defaultValue = "0") boolean ativo) {
        vendedorService.alterar(new Vendedor(ativo,matricula, nome,senha," "));
        ModelAndView modelAndView = new ModelAndView("vendedor");
        modelAndView.addObject("vendedor", new Vendedor("1", "1","1"));
        modelAndView.addObject("modalVendedor",true);
        modelAndView.addObject("NovoVendedor", new Vendedor("1", "1","1"));
        return modelAndView;
    }

    @GetMapping("delvendedor")
    public ModelAndView deletarVendedor(@RequestParam(name = "matricula") String matricula) {
        vendedorService.deletar(matricula);
        ModelAndView modelAndView = new ModelAndView("vendedor");
        modelAndView.addObject("vendedor", new Vendedor("1", "1","1"));
        modelAndView.addObject("modalVendedor",true);
        modelAndView.addObject("NovoVendedor", new Vendedor("1", "1","1"));
        return modelAndView;
    }

    @GetMapping("busvendedor")
    public ModelAndView buscarVendedor(@RequestParam(name = "matricula") String matricula) {
        Vendedor vendedor = vendedorService.buscar(matricula);
        ModelAndView modelAndView = new ModelAndView("vendedor");
        if (vendedor != null) {
            modelAndView.addObject("vendedor", vendedor);
            modelAndView.addObject("ativo", vendedor.isAtivo() ? "SIM" : "NÃO");
        } else {
            modelAndView.addObject("vendedor", new Vendedor("2", "1","1"));
            modelAndView.addObject("ativo","-");
        }
        modelAndView.addObject("modalVendedor",true);
        modelAndView.addObject("NovoVendedor",new Vendedor("1", "1","1"));
        return modelAndView;
    }

    @GetMapping("listarVendedorVendas")
    public ModelAndView listarVendedorVendas() {
        Map<Vendedor,Integer> vendedoresVendas =  vendaService.listarVendedoresPorQuantidadeVendas();
        List<Vendedor> vendedores = vendedorService.listar();
        ModelAndView modelAndView = new ModelAndView("vendedor");
        modelAndView.addObject("vendedoresVendas", vendedoresVendas);
        modelAndView.addObject("vendedores", vendedores);
        modelAndView.addObject("vendedor", new Vendedor("1", "1","1"));
        modelAndView.addObject("modalVendedor",true);
        modelAndView.addObject("NovoVendedor", new Vendedor("1", "1","1"));
        return modelAndView;
    }

    @GetMapping("listarVendedorValor")
    public ModelAndView listarVendedorValor() {
        Map<Vendedor,Double> vendedoresValor = vendaService.listarVendedoresPorValorVendido();
        List<Vendedor> vendedores = vendedorService.listar();


        ModelAndView modelAndView = new ModelAndView("vendedor");

        modelAndView.addObject("vendedoresValor", vendedoresValor);
        modelAndView.addObject("vendedorees", vendedores);

        modelAndView.addObject("vendedor", new Vendedor("1", "1","1"));
        modelAndView.addObject("modalVendedor",true);
        modelAndView.addObject("NovoVendedor", new Vendedor("1", "1","1"));
        return modelAndView;
    }
}
