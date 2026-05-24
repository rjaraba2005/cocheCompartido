package ing.soft.cocheCompartido.repository;

import ing.soft.cocheCompartido.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Heredamos la funcion de Spring Data JPA
@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    // Añadimos la funcion de buscar un viaje con un origen y un destino utilizando Spring con comandos SQL
    List<Viaje> findByOrigenAndDestino(String origen, String destino);
}