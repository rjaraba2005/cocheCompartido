package ing.soft.cocheCompartido.service;

import ing.soft.cocheCompartido.model.Reserva;
import ing.soft.cocheCompartido.model.Viaje;
import ing.soft.cocheCompartido.repository.ReservaRepository;
import ing.soft.cocheCompartido.repository.ViajeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservasService {

    private final ReservaRepository reservaRepository;
    private final ViajeRepository viajeRepository;
    private final PagosService pagosService;
    private final MessageBrokerFake messageBroker;

    // Constructor
    public ReservasService(ReservaRepository reservaRepository, ViajeRepository viajeRepository, 
                           PagosService pagosService, MessageBrokerFake messageBroker) {
        this.reservaRepository = reservaRepository;
        this.viajeRepository = viajeRepository;
        this.pagosService = pagosService;
        this.messageBroker = messageBroker;
    }

    // Reserva de un viaje
    public Reserva crearReserva(Long viajeId, Reserva nuevaReserva) {
        // Buscamos el viaje en la base de datos y sino devolvemos error
        Viaje viaje = viajeRepository.findById(viajeId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el viaje con ID: " + viajeId + "\n"));

        // Comprobamos si hay plazas suficientes
        if (viaje.getPlazas() < nuevaReserva.getPlazas()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No quedan suficientes plazas libres en este viaje \n");
        }

        // Actualizamos las plazas del viaje y lo guardamos
        viaje.setPlazas(viaje.getPlazas() - nuevaReserva.getPlazas());
        viajeRepository.save(viaje);

        // Confirmamos la reserva y la asociamos al viaje
        nuevaReserva.setEstado("Confirmada");
        nuevaReserva.setViaje(viaje);

        // retenemos el dinero de la reserva
        pagosService.retenerFondos(1L, viaje.getPrecio() * nuevaReserva.getPlazas());

        // Lo guardamos en la base de datos
        Reserva reservaGuardada = reservaRepository.save(nuevaReserva);

        // Subimos el evento al broker
        messageBroker.publicarEvento("ReservaCreada", reservaGuardada.getId());

        return reservaGuardada;
    }
}