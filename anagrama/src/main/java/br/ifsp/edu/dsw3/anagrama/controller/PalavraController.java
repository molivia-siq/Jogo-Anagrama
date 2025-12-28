package br.ifsp.edu.dsw3.anagrama.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.edu.dsw3.anagrama.model.dao.PalavraDAO;
// import br.ifsp.edu.dsw3.anagrama.model.dao.domain.Nivel;
import br.ifsp.edu.dsw3.anagrama.model.dao.domain.Palavra;
// import br.ifsp.edu.dsw3.anagrama.model.dao.domain.Tema;

@RestController
@RequestMapping({"/palavras"})
public class PalavraController {

   @Autowired
   private PalavraDAO dao;

   @PostMapping({"/cadastrar"})
   public Palavra cadastrar(@RequestParam("palavra") String palavra, 
   @RequestParam("pontuacao") Integer pontuacao, @RequestParam("nivel") Integer nivel, 
   @RequestParam("tema") Integer tema) {

      List<Palavra> pls = this.dao.findAll();

      for (Palavra pl : pls){

         if(pl.getPalavra().equals(palavra)){
            return null;

         }
      }

      Palavra p = new Palavra(palavra, pontuacao, nivel, tema);
      return (Palavra)this.dao.save(p);
   }

   @GetMapping({"/listar"})
   public List<Palavra> listar() {
      return this.dao.findAll();
   }

   @GetMapping({"/pesquisar/{id}"})
   public Palavra pesquisarPorCodigo(@PathVariable("id") Long id) {
      return (Palavra)this.dao.findById(id).orElseGet(() -> {
         return null;
     });
   }

   @DeleteMapping({"/deletar/{id}"})
   public Boolean remover(@PathVariable("id") Long id) {

      Optional<Palavra> oPalavra = this.dao.findById(id);

      if (oPalavra.isPresent()) {
         Palavra p = oPalavra.get();
         this.dao.delete(p);
         return true;
     } else {
         return false;
     }
   }

   public List<String> embaralhar() {

        int max = this.dao.findAll().size() + 1;
        Random random = new Random();
        Long numeroAleatorio = random.nextLong(max - 0 + 1) + 0;
        String palavra = this.dao.findById(numeroAleatorio).get().getPalavra();

        char[] caracteres = palavra.toCharArray();
        for (int i = caracteres.length - 1; i > 0; i--) {
            int indiceAleatorio = random.nextInt(i + 1);
            char temp = caracteres[i];
            caracteres[i] = caracteres[indiceAleatorio];
            caracteres[indiceAleatorio] = temp;
        }

        String novaP = new String(caracteres);
        List<String> lista = new ArrayList<>();

        lista.add(novaP);
        lista.add(palavra);

        return lista;
    }
}