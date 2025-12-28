package br.ifsp.edu.dsw3.anagrama.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ifsp.edu.dsw3.anagrama.model.dao.domain.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    
}
