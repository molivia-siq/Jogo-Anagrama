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

import br.ifsp.edu.dsw3.anagrama.model.dao.TemaDAO;
// import br.ifsp.edu.dsw3.anagrama.model.dao.domain.Nivel;
import br.ifsp.edu.dsw3.anagrama.model.dao.domain.Tema;

@RestController
@RequestMapping({ "/temas" })
public class TemaController {

    @Autowired
    private TemaDAO dao;

    @PostMapping({ "/cadastrar" })
    public Tema cadastrar(@RequestParam("tema") String tema, @RequestParam("nivel") Integer nivel) {

        List<Tema> tms = this.dao.findAll();

        for (Tema tm : tms){

            if(tm.getTema().equals(tema)){
                return null;

            }
        }

        Tema t = new Tema(tema, nivel);
        return (Tema) this.dao.save(t);
    }

    @GetMapping({ "/listar" })
    public List<Tema> listar() {
        return this.dao.findAll();
    }

    @GetMapping({ "/pesquisar/{id}" })
    public Tema pesquisarPorCodigo(@PathVariable("id") Long id) {
        return (Tema) this.dao.findById(id).orElseGet(() -> {
            return null;
        });
    }

    @DeleteMapping({ "/deletar/{id}" })
    public Boolean remover(@PathVariable("id") Long id) {

        Optional<Tema> oTema = this.dao.findById(id);

        if (oTema.isPresent()) {
            Tema t = oTema.get();
            this.dao.delete(t);
            return true;
        } else {
            return false;
        }
    }
}