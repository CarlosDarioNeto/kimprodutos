package br.com.carlos.mentoriakimvendedores;

import br.com.carlos.mentoriakimvendedores.entidade.Produto;
import br.com.carlos.mentoriakimvendedores.entidade.Vendedor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackageClasses = {Produto.class})
@SpringBootApplication
public class MentoriakimvendedoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentoriakimvendedoresApplication.class, args);
	}
}

/*
 * um repository para cada entidade ou um generico <object,object>
 * no produtoservice, chamo o cadastrar da classe ou o repository no deletar por exemplo
 * Desempenho - pegando tudo do DB, principalmente para os 'joins'?
 */

