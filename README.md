# Coche Compartido

Backend en **Spring Boot 4** + frontend simple para la práctica de OO

Implementa los cuatro casos de uso de la 2ª entrega:

1. **Reserva de un viaje**
2. **Publicación de viaje** (con sincronización CQRS simulada)
3. **Cancelación del trayecto**
4. **Finalización del trayecto**

El proyecto sigue la estructura del ejemplo de GRISE‑UPM ([EjemploSpringBoot-producto](https://github.com/GRISE-UPM/EjemploSpringBoot-producto)): controllers REST delgados, services con la lógica de negocio, repositorios JPA y un `MessageBrokerFake` que simula la comunicación asíncrona entre microservicios (pagos, notificaciones, reseñas).

## Cómo arrancarlo

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

## Datos de prueba

Al arrancar, [`data.sql`](src/main/resources/data.sql) carga automáticamente 3 usuarios, 2 vehículos y 3 viajes para poder probar el flujo sin tener que crear todo a mano.

## Autores

Rubén Jaraba Sánchez · Clara García Toledo · Pablo Campo Herrero · Alexandra Pinto Ghaffar
