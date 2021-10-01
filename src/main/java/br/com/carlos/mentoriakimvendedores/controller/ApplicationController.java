package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.entity.Salesman;
import br.com.carlos.mentoriakimvendedores.service.SalesmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {

    private final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
    @Autowired
    private SalesmanService salesmanService;

    @GetMapping()
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("nome", salesmanService.getLoggedUsername());
        modelAndView.addObject("modalVendedor", true);
        modelAndView.addObject("NovoVendedor", new Salesman());
        return modelAndView;
    }

    @RequestMapping("/login.html")
    public String login() {
        return "login.html";
    }

    @GetMapping("login")
    public ModelAndView getLogin(){
        return new ModelAndView("login");
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

    @GetMapping("cadastrar-primeira-vez")
    public ModelAndView primeiroCadastro(){
        return new ModelAndView("primeiro-cadastro");
    }

    @GetMapping("primeiro_cadastro")
    public ModelAndView validandoPrimeiroCadastro(@RequestParam(name = "nome")String name,
                                                  @RequestParam(name = "senha")String password,
                                                  @RequestParam(name = "codigo")String code){
        Salesman salesman=new Salesman();
        if(code.equals("1234")){
            salesman = salesmanService.cadastrar(new Salesman("1",name,password));
        }
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("modalVendedor", false);
        modelAndView.addObject("NovoVendedor", salesman);
        return modelAndView;
    }
}