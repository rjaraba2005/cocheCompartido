package ing.soft.cocheCompartido.service;

import org.springframework.stereotype.Service;

@Service 
// Simulamos las conexiones con el banco y suponemos que siempre salen bien
public class PagosService {

    // Creamos la función boolean para simular en la realidad que algo pudiese salir mal y devolver false
    public boolean procesarReembolso(Long idReferencia) {
        System.out.println("Pagos: reembolsando dinero del viaje con ID: " + idReferencia);
        // Siempre sale bien asi que devolvemos true
        return true; 
    }
    
    public boolean retenerFondos(Long pasajeroId, Double cantidad) {
        System.out.println("Pagos: Retención de " + cantidad + "€ del viaje con ID " + pasajeroId);
        return true;
    }

    public boolean procesarPago(Long idReferencia){
        System.out.println("Pagos: reembolsando dinero del viaje con ID: " + idReferencia);
        return true;
    }
}