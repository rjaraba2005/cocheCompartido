package ing.soft.cocheCompartido.controller;

import ing.soft.cocheCompartido.model.Vehiculo;
import ing.soft.cocheCompartido.repository.VehiculoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehiculosRESTController {

    private final VehiculoRepository vehiculoRepository;

    public VehiculosRESTController(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @GetMapping("/vehiculos")
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }
}
