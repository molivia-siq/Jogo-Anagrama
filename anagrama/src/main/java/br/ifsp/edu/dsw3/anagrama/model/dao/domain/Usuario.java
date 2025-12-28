package br.ifsp.edu.dsw3.anagrama.model.dao.domain;

import java.io.Serializable;
// import java.util.List;

import lombok.*;  // getters e setters
import jakarta.persistence.*; // o resto dos @

@Getter
@Setter
@Entity(name = "usuarios")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String senha;

    // @Column(insertable = false)
    private int pontuacao;

    // private List<Palavra> historico;

    public Usuario(String nome, String senha, int pontuacao) {
        this.senha = senha;
        this.nome = nome;
        this.pontuacao = pontuacao;
        // this.historico = historico;
    }

    public Usuario(String nome, String senha) {
        this.senha = senha;
        this.nome = nome;
    }

    public Usuario() {
    }



}
