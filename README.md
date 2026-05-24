# Coche Compartido

Backend en **Spring Boot 4** + frontend simple en HTML/CSS/JS para la práctica de OO / Ingeniería del Software (ETSIInf, UPM).

Implementa los cuatro casos de uso de la 2ª entrega:

1. **Reserva de un viaje**
2. **Publicación de viaje** (con sincronización CQRS simulada)
3. **Cancelación del trayecto**
4. **Finalización del trayecto**

El proyecto sigue la estructura del ejemplo de GRISE‑UPM ([EjemploSpringBoot-producto](https://github.com/GRISE-UPM/EjemploSpringBoot-producto)): controllers REST delgados, services con la lógica de negocio, repositorios JPA y un `MessageBrokerFake` que simula la comunicación asíncrona entre microservicios (pagos, notificaciones, reseñas).

## Cómo arrancarlo

Requisitos: **Java 17+** (probado con 20) y conexión a internet la primera vez (Maven descarga dependencias).

Desde la raíz del proyecto:

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

Cuando veas en la consola `Started CocheCompartidoApplication`, abre el navegador en:

- **Frontend**: http://localhost:8080/
- **Swagger UI** (documentación de la API): http://localhost:8080/swagger-ui.html
- **H2 Console** (base de datos en memoria): http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - User: `sa` (sin contraseña)

Para parar el servidor: `Ctrl + C` en la terminal.

## Endpoints REST

| Método | Ruta                                  | Descripción                                  |
|--------|---------------------------------------|----------------------------------------------|
| GET    | `/viajes`                             | Listar todos los viajes                      |
| GET    | `/viajes/{id}`                        | Obtener un viaje por ID                      |
| GET    | `/viajes/buscar?origen=X&destino=Y`   | Buscar viajes por origen y destino           |
| POST   | `/viajes`                             | Publicar un viaje nuevo                      |
| DELETE | `/viajes/{id}`                        | Cancelar un viaje                            |
| PUT    | `/viajes/{id}/estadoFinalizado`       | Finalizar un viaje                           |
| POST   | `/viajes/{viajeId}/reservas`          | Crear una reserva para un viaje              |
| GET    | `/usuarios`                           | Listar usuarios                              |
| GET    | `/vehiculos`                          | Listar vehículos                             |

## Datos de prueba

Al arrancar, [`data.sql`](src/main/resources/data.sql) carga automáticamente 3 usuarios, 2 vehículos y 3 viajes para poder probar el flujo sin tener que crear todo a mano.

## Estructura del proyecto

```
src/main/java/ing/soft/cocheCompartido/
├── CocheCompartidoApplication.java
├── controller/    # REST: Viajes, Reservas, Usuarios, Vehiculos + GlobalExceptionHandler
├── service/       # Lógica: ViajesService, ReservasService, PagosService,
│                  #         NotificacionesService, ResenasService, MessageBrokerFake
├── repository/    # JPA: Viaje/Reserva/Usuario/Vehiculo Repository
└── model/         # Entidades: Viaje, Reserva, Usuario, Vehiculo + enums

src/main/resources/
├── application.properties
├── data.sql
└── static/        # Frontend: index.html, styles.css, app.js

docs/              # PDF de la 2ª entrega + DOCX de los 4 casos de uso
```

## Autores

Rubén Jaraba Sánchez · Clara García Toledo · Pablo Campo Herrero · Alexandra Pinto Ghaffar
