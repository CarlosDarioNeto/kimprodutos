package br.com.carlos.mentoriakimvendedores.entity;

import javax.persistence.*;

@Entity
@Table(name = "vendedor")
public class Salesman {
    @Column(name = "nome")
    private String nome;

    @Column(name = "matricula")
    @Id
    private String matricula;

    @Column(name = "active")
    private boolean ativo;

    @Column
    private String password;

    @Column
    private String roles;

    public Salesman(){

    }

    public Salesman(boolean ativo, String matricula, String nome, String password, String roles) {
        this.ativo = ativo;
        this.nome = nome;
        this.matricula = matricula;
        this.roles = roles;
        this.password = password;
    }

    public Salesman(String matricula, String nome, String password) {
        this.ativo = true;
        this.nome = nome;
        this.matricula = matricula;
        this.password = password;
        this.roles = "ROLE_ADMIN";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
