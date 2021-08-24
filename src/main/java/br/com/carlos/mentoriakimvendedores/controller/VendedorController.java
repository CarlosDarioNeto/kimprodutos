package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import br.com.carlos.mentoriakimvendedores.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Tuple;
import java.util.List;

@Controller()
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/vendedor")
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
                                        @RequestParam(name = "matricula") String matricula,
                                        @RequestParam(name = "ativar", defaultValue = "0") char ativo) {
        System.out.println(ativo);
        if(ativo=='1'){
            vendedorService.alterar(new Vendedor('1',matricula, nome));
        }else{
            vendedorService.alterar(new Vendedor(matricula, nome));
        }
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
            modelAndView.addObject("ativo", vendedor.getAtivo()=='1' ? "SIM" : "NÃO");
        } else {
            modelAndView.addObject("vendedor", new Vendedor("2", "1"));
            modelAndView.addObject("ativo","-");
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
}
