package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.service.SalesmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {

    private final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
    @Autowired
    private SalesmanService salesmanService;

    @GetMapping()
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("nome", "null");
        return modelAndView;
    }

    @RequestMapping("/login.html")
    public String login() {
        return "login.html";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }
}