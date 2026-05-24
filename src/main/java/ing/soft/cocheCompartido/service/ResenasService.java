package ing.soft.cocheCompartido.service;

import org.springframework.stereotype.Service;

@Service 
public class ResenasService {

    // Simulamos las reseñas del viaje
    public void habilitarResenas(Long viajeId) {
        System.out.println("Reseñas: Los usuarios ya pueden publicar sus reseñas del viaje con ID: " + viajeId);
    }
}