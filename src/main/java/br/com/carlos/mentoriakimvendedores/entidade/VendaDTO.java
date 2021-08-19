package br.com.carlos.mentoriakimvendedores.entidade;

import java.util.ArrayList;
import java.util.List;

public class VendaDTO {
    private List<TabelaProdutos> itens=new ArrayList<>();
    private String matricula;

    public VendaDTO(List<TabelaProdutos> itens, String matricula) {
        this.itens = itens;
        this.matricula = matricula;
    }

    public VendaDTO(){}

    public List<TabelaProdutos> getItens() {
        return itens;
    }

    public void setItens(List<TabelaProdutos> itens) {
        this.itens = itens;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
