package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.entidade.VendaDTO;
import br.com.carlos.mentoriakimvendedores.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VendaController {

    @Autowired
    private VendaService vendaService;

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
