package ing.soft.cocheCompartido.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Usuario {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String email;
    private String telefono;
    private String contrasena;
    private String dni;
    private Double reputacion;

    // Un conductor puede tener muchos viajes
    @OneToMany(mappedBy = "conductor")
    @JsonIgnore
    private List<Viaje> viajesPublicados;

    // Un pasajero puede tener muchas reservas
    @OneToMany(mappedBy = "pasajero")
    @JsonIgnore
    private List<Reserva> reservas;

    public Usuario() {
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Double getReputacion() {
        return reputacion;
    }

    public void setReputacion(Double reputacion) {
        this.reputacion = reputacion;
    }

    public List<Viaje> getViajesPublicados() {
        return viajesPublicados;
    }

    public void setViajesPublicados(List<Viaje> viajesPublicados) {
        this.viajesPublicados = viajesPublicados;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    
}