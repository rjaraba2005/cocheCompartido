package ing.soft.cocheCompartido.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origen;
    private String destino;
    private LocalDate fecha;
    private Integer plazas;
    private Double precio;

    private Boolean mascotas;
    private Boolean ninos;
    private Boolean soloMujeres;
    private Boolean paradas;

    @Enumerated(EnumType.STRING)
    private EstadoViaje estado;

    // Varios viajes de un conductor
    @ManyToOne
    @JoinColumn(name = "conductor_id")
    private Usuario conductor;

    // Varios viajes de un vehiculo
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    // Un viaje tiene muchos usuarios
    @OneToMany(mappedBy = "viaje")
    // Para evitar problemas de bucles infinitos
    @JsonIgnore
    private List<Reserva> reservas;

    public Viaje() {
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getPlazas() {
        return plazas;
    }

    public void setPlazas(Integer plazas) {
        this.plazas = plazas;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getMascotas() {
        return mascotas;
    }

    public void setMascotas(Boolean mascotas) {
        this.mascotas = mascotas;
    }

    public Boolean getNinos() {
        return ninos;
    }

    public void setNinos(Boolean ninos) {
        this.ninos = ninos;
    }

    public Boolean getSoloMujeres() {
        return soloMujeres;
    }

    public void setSoloMujeres(Boolean soloMujeres) {
        this.soloMujeres = soloMujeres;
    }

    public Boolean getParadas() {
        return paradas;
    }

    public void setParadas(Boolean paradas) {
        this.paradas = paradas;
    }

    public EstadoViaje getEstado() {
        return estado;
    }

    public void setEstado(EstadoViaje estado) {
        this.estado = estado;
    }

    public Usuario getConductor() {
        return conductor;
    }

    public void setConductor(Usuario conductor) {
        this.conductor = conductor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
}