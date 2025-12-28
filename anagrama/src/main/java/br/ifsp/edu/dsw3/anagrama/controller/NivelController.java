package br.ifsp.edu.dsw3.anagrama.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.edu.dsw3.anagrama.model.dao.NivelDAO;
import br.ifsp.edu.dsw3.anagrama.model.dao.domain.Nivel;

@RestController
@RequestMapping({"/niveis"})
public class NivelController {

   @Autowired
   private NivelDAO dao;

   @PostMapping({"/cadastrar"})
   public Nivel cadastrar(@RequestParam("tipo") String tipo) {

      List<Nivel> nvs = this.dao.findAll();

      for (Nivel nv : nvs){

         if(nv.getTipo().equals(tipo)){
            return null;

         }
      }

      Nivel n = new Nivel(tipo);
      return (Nivel) this.dao.save(n);
   }

   @GetMapping({"/listar"})
   public List<Nivel> listar() {
      return this.dao.findAll();
   }

   @GetMapping({"/pesquisar/{id}"})
   public Nivel pesquisarPorCodigo(@PathVariable("id") Long id) {
      return (Nivel)this.dao.findById(id).orElseGet(() -> {
         return null;
     });
   }

   @DeleteMapping({"/deletar/{id}"})
   public Boolean remover(@PathVariable("id") Long id) {

      Optional<Nivel> oNivel = this.dao.findById(id);

      if (oNivel.isPresent()) {
         Nivel n = oNivel.get();
         this.dao.delete(n);
         return true;
     } else {
         return false;
     }
   }
}