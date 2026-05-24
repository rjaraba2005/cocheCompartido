package ing.soft.cocheCompartido.repository;

import ing.soft.cocheCompartido.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Heredamos la funcion de Spring Data JPA
@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}