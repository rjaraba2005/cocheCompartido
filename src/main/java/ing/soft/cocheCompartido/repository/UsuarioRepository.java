package ing.soft.cocheCompartido.repository;

import ing.soft.cocheCompartido.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Heredamos la funcion de Spring Data JPA
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}