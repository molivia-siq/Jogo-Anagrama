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

import br.ifsp.edu.dsw3.anagrama.model.dao.UsuarioDAO;
import br.ifsp.edu.dsw3.anagrama.model.dao.domain.Usuario;

@RestController
@RequestMapping({ "/usuarios" })
public class UsuarioController {

   @Autowired
   private UsuarioDAO dao;

   @PostMapping({ "/cadastrar" })
   public Usuario cadastrar(@RequestParam("nome") String nome, @RequestParam("senha") String senha,
         @RequestParam("pontuacao") int pontuacao) {

      List<Usuario> usrs = this.dao.findAll();

      for (Usuario us : usrs){

         if(us.getNome().equals(nome)){
            return null;

         }
      }

      Usuario u = new Usuario(nome, senha, pontuacao);
      return (Usuario) this.dao.save(u);
   }

   @GetMapping({ "/listar" })
   public List<Usuario> listar() {
      return this.dao.findAll();
   }

   @GetMapping({ "/pesquisar/{id}" })
   public Usuario pesquisarPorCodigo(@PathVariable("id") Long id) {
      return (Usuario) this.dao.findById(id).orElseGet(() -> {
         return null;
     });
   }

   @DeleteMapping({ "/deletar/{id}" })
   public Boolean remover(@PathVariable("id") Long id) {

      Optional<Usuario> oUsuario = this.dao.findById(id);

      if (oUsuario.isPresent()) {
         Usuario u = oUsuario.get();
         this.dao.delete(u);
         return true;
     } else {
         return false;
     }
   }
}