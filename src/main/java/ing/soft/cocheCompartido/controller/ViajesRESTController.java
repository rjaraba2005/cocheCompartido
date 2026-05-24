package ing.soft.cocheCompartido.controller;

import ing.soft.cocheCompartido.model.Viaje;
import ing.soft.cocheCompartido.service.ViajesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Utilizamos esta clase como controller
@RestController 
public class ViajesRESTController {

    private final ViajesService viajesService;

    // Constructor
    public ViajesRESTController(ViajesService viajesService) {
        this.viajesService = viajesService;
    }

    @PostMapping("/viajes")
    // Se asegura de que todo va bien
    @ResponseStatus(HttpStatus.CREATED) 
    // Utilizamos RequestBody para crear un objeto del viaje
    public Viaje publicarViaje(@RequestBody Viaje nuevoViaje) {
        return viajesService.publicarViaje(nuevoViaje);
    }

    @DeleteMapping("/viajes/{id}")
    // Usamos PathVariable para extraer el dato id
    public Viaje cancelarViaje(@PathVariable Long id) {
        return viajesService.cancelarViaje(id);
    }

    @PutMapping("/viajes/{id}/estadoFinalizado")
    // Usamos PathVariable para extraer el dato id
    public Viaje finalizarViaje(@PathVariable Long id) {
        return viajesService.finalizarViaje(id);
    }

    @GetMapping("/viajes/buscar")
    // Usamos RequestParam para extraer los datos de consulta del usuario
    public List<Viaje> buscarViajes(@RequestParam String origen, @RequestParam String destino) {
        return viajesService.buscarViajes(origen, destino);
    }

    @GetMapping("/viajes")
    public List<Viaje> listarViajes() {
        return viajesService.listarViajes();
    }

    @GetMapping("/viajes/{id}")
    public Viaje obtenerViaje(@PathVariable Long id) {
        return viajesService.obtenerViaje(id);
    }
}