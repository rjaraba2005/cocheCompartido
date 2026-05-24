package ing.soft.cocheCompartido.repository;
import ing.soft.cocheCompartido.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Heredamos la funcion de Spring Data JPA
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}