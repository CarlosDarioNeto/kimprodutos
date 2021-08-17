package br.com.carlos.mentoriakimvendedores.entidade;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item  {
    @Id
    private String id;  //id_venda + numero do item
    @Column
    private int id_produto;
    @Column
    private int quantidade;

    public Item(String id, int id_produto, int quantidade) {
        this.id = id;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
    }

    public Item(int id_produto, int quantidade) {
        this.id = id;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
    }

    public Item(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
