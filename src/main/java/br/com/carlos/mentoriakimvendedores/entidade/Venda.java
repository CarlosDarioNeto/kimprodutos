package br.com.carlos.mentoriakimvendedores.entidade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venda")
public class Venda {
    @Id
    private String id; //21 08 16 08 02 55 ano/mes/dia/hora/min/seg
    @Column
    private String matricula_vendedor;
    @Column(name = "valor_total")
    private double valor_total;
    @OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venda")
    private List<Item> itens = new ArrayList<>();

    public Venda(String id, String matricula_vendedor, double valor_total, List<Item> itens) {
        this.id = id;
        this.matricula_vendedor = matricula_vendedor;
        this.valor_total = valor_total;
        this.itens = itens;
    }

    public Venda( String matricula_vendedor,List<Item> itens, double valor_total) {
        this.matricula_vendedor = matricula_vendedor;
        this.itens = itens;
        this.valor_total = valor_total;
    }

    public Venda() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatricula_vendedor() {
        return matricula_vendedor;
    }

    public void setMatricula_vendedor(String matricula_vendedor) {
        this.matricula_vendedor = matricula_vendedor;
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
}
