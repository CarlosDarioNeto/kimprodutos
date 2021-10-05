package br.com.carlos.mentoriakimvendedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MentoriakimvendedoresApplication {

    public static void main(String[] args) {
        SpringApplication.run(MentoriakimvendedoresApplication.class, args);
    }

    /* Salesman extends user
    *  Saleman com a matricula sendo FK da tabela user
    *  Saleman sendo refernciado apenas como um role*/
}
