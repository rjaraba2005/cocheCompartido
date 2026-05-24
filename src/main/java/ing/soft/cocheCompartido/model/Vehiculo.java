package ing.soft.cocheCompartido.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String permisoConducirId;
    private String marca;
    private String modelo;
    private String color;

    // Un vehiculo para muchos viajes
    @OneToMany(mappedBy = "vehiculo")
    @JsonIgnore
    private List<Viaje> viajes;

    public Vehiculo() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermisoConducirId() {
        return permisoConducirId;
    }

    public void setPermisoConducirId(String permisoConducirId) {
        this.permisoConducirId = permisoConducirId;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    
}