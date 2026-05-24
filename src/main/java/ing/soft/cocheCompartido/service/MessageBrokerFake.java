package ing.soft.cocheCompartido.service;

import org.springframework.stereotype.Service;

@Service
public class MessageBrokerFake {

    private final PagosService pagosService;
    private final NotificacionesService notificacionesService;
    private final ResenasService resenasService;

    // Constructor
    public MessageBrokerFake(PagosService pagosService, NotificacionesService notificacionesService,  ResenasService resenasService) {
        this.pagosService = pagosService;
        this.notificacionesService = notificacionesService;
        this.resenasService = resenasService;
    }

    // Simulamos que publicamos un evento al broker y que le llega al resto de microservicios
    public void publicarEvento(String tipoEvento, Long idReferencia) {
        System.out.println("Broker: Evento recibido: " + tipoEvento + " para el ID: " + idReferencia);

        // Simulamos el comportamiento que tendria con los otros microservicios
        if (tipoEvento.equals("ViajeCancelado")) {
            // Simulamos el reembolso del dinero
            pagosService.procesarReembolso(idReferencia);
            // Simulamos la notificacion a los usuarios
            notificacionesService.enviarPush("El viaje " + idReferencia + " ha sido cancelado");
        } 
        else if (tipoEvento.equals("ViajeFinalizado")) {
            // Simulamos el pago al conductor
            pagosService.procesarPago(idReferencia); 
            // Simulamos el inicio de las reseñas del viaje
            resenasService.habilitarResenas(idReferencia);
            // Simulamos la notificacion al usuario
            notificacionesService.enviarPush("El viaje con ID: " + idReferencia + " ha terminado. Deja tu reseña!");
        }
        System.out.println();
    }
}