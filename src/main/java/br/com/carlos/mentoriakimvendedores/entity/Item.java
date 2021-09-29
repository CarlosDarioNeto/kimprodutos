package br.com.carlos.mentoriakimvendedores.entity;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item  {
    @Id
    private String id;
    @Column
    private int id_produto;
    @Column
    private int quantidade;
    @Column
    private double preco_corrente;

    public Item(String id, int id_produto, int quantidade, double preco_corrente) {
        this.id = id;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_corrente = preco_corrente;
    }

    public Item(int id_produto, int quantidade) {
        this.id_produto = id_produto;
        this.quantidade = quantidade;
    }

    public Item(int id_produto, int quantidade,double preco_corrente) {
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_corrente = preco_corrente;
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

    public double getPreco_corrente() {
        return preco_corrente;
    }

    public void setPreco_corrente(double preco_corrente) {
        this.preco_corrente = preco_corrente;
    }
}
