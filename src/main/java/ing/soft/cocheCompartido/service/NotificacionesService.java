package ing.soft.cocheCompartido.service;

import org.springframework.stereotype.Service;

@Service
public class NotificacionesService {

    public void enviarPush(String mensaje) {
        System.out.println("Notificaciones: " + mensaje);
    }
}