package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.service.VendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {

    private final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
    @Autowired
    VendaService vendaService;


    @GetMapping()
    public ModelAndView showHome() {
        vendaService.listarVendedoresPorValorVendido();
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
}