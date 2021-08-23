package br.com.carlos.mentoriakimvendedores.entidade;

import javax.persistence.*;

@Entity
@NamedQuery(name = "produto.list", query = "SELECT p FROM Produto p ")
@Table(name = "produto")
public class Produto {
    @Id
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "preco")
    private double valor;
    @Column
    private char ativo;

    public Produto(int id, String nome, double valor, char ativo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.ativo = ativo;
    }

    public Produto(String nome, double valor, char ativo) {
        this.nome = nome;
        this.valor = valor;
        this.ativo = ativo;
    }

    public Produto(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Produto(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Produto(){

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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }
}
