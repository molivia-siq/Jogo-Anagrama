package br.ifsp.edu.dsw3.anagrama.model.dao.domain;

import java.io.Serializable;

import lombok.*;  // getters e setters
import jakarta.persistence.*; // o resto dos @

@Getter
@Setter
@Entity(name = "temas")
public class Tema implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tema;

    @Column(nullable = false)
    // private Nivel nivel;
    private Integer id_nivel; // para testar no postman

    public Tema() {
    }

    public Tema(String tema, Integer id_nivel) {
        this.tema = tema;
        this.id_nivel = id_nivel;
    }

}
