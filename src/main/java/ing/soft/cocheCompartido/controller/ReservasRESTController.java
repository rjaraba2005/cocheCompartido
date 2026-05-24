package ing.soft.cocheCompartido.controller;

import ing.soft.cocheCompartido.model.Reserva;
import ing.soft.cocheCompartido.service.ReservasService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservasRESTController {

    private final ReservasService reservasService;

    // Constructor
    public ReservasRESTController(ReservasService reservasService) {
        this.reservasService = reservasService;
    }

    @PostMapping("/viajes/{viajeId}/reservas")
    // Se asegura de que todo va bien
    @ResponseStatus(HttpStatus.CREATED)
    // Usamos PathVariable para obtener el valor de viajeID y RequestBody para obtener el valor de la peticion del cliente
    public Reserva crearReserva(@PathVariable Long viajeId, @RequestBody Reserva nuevaReserva) {
        return reservasService.crearReserva(viajeId, nuevaReserva);
    }
}