package br.ifsp.edu.dsw3.anagrama.model.dao.domain;

import java.io.Serializable;

import lombok.*;  // getters e setters
import jakarta.persistence.*; // o resto dos @

@Getter
@Setter
@Entity(name = "palavras")
public class Palavra implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String palavra;

    @Column(nullable = false)
    private Integer pontuacao;

    @Column(nullable = false)
    // private Nivel nivel;
    private Integer id_nivel; // pra testar no postman

    @Column(nullable = false)
    // private Tema tema;
    private Integer id_tema; // pra testar no postamn

    public Palavra(String palavra, Integer pontuacao, Integer id_nivel, Integer id_tema) {
        this.palavra = palavra;
        this.pontuacao = pontuacao;
        this.id_nivel = id_nivel;
        this.id_tema = id_tema;
    }

    public Palavra() {
    }

    

}
