-- Datos de prueba para arrancar el sistema con algo de contenido

INSERT INTO Usuario (email, telefono, contrasena, dni, reputacion) VALUES ('ana@upm.es',  '600111222', 'secret1', '11111111A', 4.5);
INSERT INTO Usuario (email, telefono, contrasena, dni, reputacion) VALUES ('luis@upm.es', '600222333', 'secret2', '22222222B', 4.8);
INSERT INTO Usuario (email, telefono, contrasena, dni, reputacion) VALUES ('maria@upm.es','600333444', 'secret3', '33333333C', 4.2);

INSERT INTO Vehiculo (permiso_conducir_id, marca, modelo, color) VALUES ('B-12345', 'Seat',   'Ibiza',  'Blanco');
INSERT INTO Vehiculo (permiso_conducir_id, marca, modelo, color) VALUES ('B-67890', 'Renault','Clio',   'Rojo');

INSERT INTO Viaje (origen, destino, fecha, plazas, precio, mascotas, ninos, solo_mujeres, paradas, estado, conductor_id, vehiculo_id)
VALUES ('Madrid', 'Vigo', '2026-05-13', 4, 25.0, false, true, false, true, 'PROGRAMADO', 1, 1);

INSERT INTO Viaje (origen, destino, fecha, plazas, precio, mascotas, ninos, solo_mujeres, paradas, estado, conductor_id, vehiculo_id)
VALUES ('Madrid', 'Sevilla', '2026-06-01', 3, 30.0, true, true, false, false, 'PROGRAMADO', 2, 2);

INSERT INTO Viaje (origen, destino, fecha, plazas, precio, mascotas, ninos, solo_mujeres, paradas, estado, conductor_id, vehiculo_id)
VALUES ('Barcelona', 'Valencia', '2026-05-30', 2, 18.0, false, false, true, true, 'PROGRAMADO', 3, 1);
