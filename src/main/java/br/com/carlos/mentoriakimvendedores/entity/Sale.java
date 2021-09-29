package br.com.carlos.mentoriakimvendedores.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venda")
public class Sale {
    @Id
    private String id;

    @ManyToOne(targetEntity = Salesman.class)
    @JoinColumn(name = "matricula_vendedor")
    private Salesman salesman;

    @Column(name = "valor_total")
    private double valor_total;

    @OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venda")
    private List<Item> itens = new ArrayList<>();

    public Sale(Salesman salesman, double valor_total, List<Item> itens) {
        this.salesman = salesman;
        this.valor_total = valor_total;
        this.itens = itens;
    }

    public Sale(String id, Salesman salesman, double valor_total, List<Item> itens) {
        this.id = id;
        this.salesman = salesman;
        this.valor_total = valor_total;
        this.itens = itens;
    }

    public Sale() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Salesman getVendedor() {
        return salesman;
    }

    public void setVendedor(Salesman salesman) {
        this.salesman = salesman;
    }
}
