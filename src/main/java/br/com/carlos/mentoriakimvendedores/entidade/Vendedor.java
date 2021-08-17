package br.com.carlos.mentoriakimvendedores.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendedor")
public class Vendedor {
    @Column(name = "nome")
    private String nome;
    @Column(name = "matricula")
    @Id
    private String matricula;

    public Vendedor(String matricula,String nome) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public Vendedor(){

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
}
