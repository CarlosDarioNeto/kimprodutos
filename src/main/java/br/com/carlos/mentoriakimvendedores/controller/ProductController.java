package br.com.carlos.mentoriakimvendedores.controller;

import br.com.carlos.mentoriakimvendedores.entity.Product;
import br.com.carlos.mentoriakimvendedores.service.ProductService;
import br.com.carlos.mentoriakimvendedores.service.SalesmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SalesmanService salesmanService;

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("product")
    public ModelAndView showProduct() {
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("nome", salesmanService.getLoggedUsername());
        return modelAndView;
    }

    @GetMapping("insert_product")
    public ModelAndView insertProduct(@RequestParam(name = "nome") String name,
                                      @RequestParam(name = "valor") String value) {
        productService.cadastrar(new Product(name, Double.parseDouble(value)));
        return showProduct();
    }

    @GetMapping("update_product")
    public ModelAndView updateProduct(@RequestParam(name = "nome") String name,
                                      @RequestParam(name = "valor") String value,
                                      @RequestParam(name = "id") String id,
                                      @RequestParam(name = "ativar", defaultValue = "0") boolean isActive) {
        productService.alterar(new Product(Integer.parseInt(id), name, Double.parseDouble(value), isActive));
        return showProduct();
    }

    @GetMapping("disable_product")
    public ModelAndView desableProduct(@RequestParam(name = "id") int id) {
        productService.deletar(id);
        return showProduct();
    }

    @GetMapping("show_products")
    public ModelAndView showAllProducts() {
        List<Product> products = productService.listar();
        ModelAndView modelAndView = new ModelAndView("tabelaprodutos");
        modelAndView.addObject("products", products);
        modelAndView.addObject("nome", salesmanService.getLoggedUsername());
        return modelAndView;
    }
}
