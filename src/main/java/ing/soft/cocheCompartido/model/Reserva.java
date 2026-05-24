package ing.soft.cocheCompartido.model;

import jakarta.persistence.*;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer plazas;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    // Un pasajero puede tener varias reservas
    @ManyToOne
    @JoinColumn(name = "pasajero_id")
    private Usuario pasajero;

    // Puedes tener varias reservas para el mismo viaje
    @ManyToOne
    @JoinColumn(name = "viaje_id")
    private Viaje viaje;

    // Getters y Setters
    public Reserva() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlazas() {
        return plazas;
    }

    public void setPlazas(Integer plazas) {
        this.plazas = plazas;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public Usuario getPasajero() {
        return pasajero;
    }

    public void setPasajero(Usuario pasajero) {
        this.pasajero = pasajero;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    
}