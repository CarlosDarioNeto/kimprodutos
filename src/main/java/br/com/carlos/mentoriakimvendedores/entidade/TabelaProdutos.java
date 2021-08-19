package br.com.carlos.mentoriakimvendedores.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TabelaProdutos {
    @Id
    @Column(name = "id")
    private int id;
    @Column (name = "nome")
    private String nome;
    @Column (name = "preco")
    private double preco;
    @Column (name = "quantidade")
    private int quantidade;

    public TabelaProdutos(){

    }

    public TabelaProdutos(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
