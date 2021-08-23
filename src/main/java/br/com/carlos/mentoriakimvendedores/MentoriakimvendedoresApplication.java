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

/* Not allowed to create transaction on shared EntityManager - use Spring transactions or EJB CMT instead
*	Se for fazer faça tudo de um jeito só ou pode variar conforme ser mais adequeado? named querys, vantagem em desempenho?
	Setar o ativo no construtor da classe ou no service?
	* Venda service muitas consultas ao DB, ruim né? que tal uma variavel global?
* */

