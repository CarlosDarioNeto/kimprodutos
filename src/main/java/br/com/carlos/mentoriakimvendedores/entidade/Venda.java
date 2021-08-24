package br.com.carlos.mentoriakimvendedores.entidade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "list.venda",query = "select v.")
@Entity
@Table(name = "venda")
public class Venda {
    @Id
    private String id;
    @ManyToOne(targetEntity = Vendedor.class)
    @JoinColumn(name = "matricula_vendedor")
    private Vendedor vendedor;
    @Column(name = "valor_total")
    private double valor_total;
    @OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venda")
    private List<Item> itens = new ArrayList<>();

    public Venda(Vendedor vendedor, double valor_total, List<Item> itens) {
        this.vendedor = vendedor;
        this.valor_total = valor_total;
        this.itens = itens;
    }

    public Venda(String id, Vendedor vendedor, double valor_total, List<Item> itens) {
        this.id = id;
        this.vendedor = vendedor;
        this.valor_total = valor_total;
        this.itens = itens;
    }

    public Venda() {

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

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
}
