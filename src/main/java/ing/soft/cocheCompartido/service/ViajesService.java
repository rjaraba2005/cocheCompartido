package ing.soft.cocheCompartido.service;

import ing.soft.cocheCompartido.model.Viaje;
import ing.soft.cocheCompartido.repository.ViajeRepository;
import ing.soft.cocheCompartido.model.EstadoViaje;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ViajesService {

    private final ViajeRepository viajeRepository;
    private final MessageBrokerFake messageBroker;

    // Constructor
    public ViajesService(ViajeRepository viajeRepository, MessageBrokerFake messageBroker) {
        this.viajeRepository = viajeRepository;
        this.messageBroker = messageBroker;
    }

    // Publicación de viaje
    public Viaje publicarViaje(Viaje nuevoViaje) {

        // Cambiamos el estado del viaje a Programado
        nuevoViaje.setEstado(EstadoViaje.PROGRAMADO);
        // Lo guardamos en la base de datos
        Viaje viajeGuardado = viajeRepository.save(nuevoViaje);

        // Publicamos el evento en el broker
        messageBroker.publicarEvento("ViajePublicado", viajeGuardado.getId());

        return viajeGuardado;
    }

    // Cancelación de trayecto
    public Viaje cancelarViaje(Long viajeId) {
        // Buscamos el viaje y si no lo encontramos devolvemos error
        Viaje viaje = viajeRepository.findById(viajeId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el viaje con ID: " + viajeId + "\n"));

        // Cambiamos el estado del viaje a Cancelado
        viaje.setEstado(EstadoViaje.CANCELADO);
        // Guardamos el nuevo viaje cancelado
        Viaje viajeGuardado = viajeRepository.save(viaje);

        // Publicamos el evento en el broker
        messageBroker.publicarEvento("ViajeCancelado", viajeId);

        return viajeGuardado;
    }

    // Finalización de trayecto
    public Viaje finalizarViaje(Long viajeId) {
        // Buscamos el viaje y si no lo encontramos devolvemos error
        Viaje viaje = viajeRepository.findById(viajeId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el viaje con ID: " + viajeId + "\n"));

        // Cambiamos el estado del viaje a Cancelado
        viaje.setEstado(EstadoViaje.FINALIZADO);
        // Guardamso el viaje en la base de datos
        Viaje viajeGuardado = viajeRepository.save(viaje);

        // Publicamos el evento en el broker
        messageBroker.publicarEvento("ViajeFinalizado", viajeId);
        return viajeGuardado;
    }

    // Microservicio ViajesSearch dentro de Viajes
    public List<Viaje> buscarViajes(String origen, String destino) {
        return viajeRepository.findByOrigenAndDestino(origen, destino);
    }

    public List<Viaje> listarViajes() {
        return viajeRepository.findAll();
    }

    public Viaje obtenerViaje(Long viajeId) {
        return viajeRepository.findById(viajeId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el viaje con ID: " + viajeId));
    }
}