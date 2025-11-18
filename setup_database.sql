-- ============================================
-- PROYECTO: 5 DE ORO
-- DESCRIPCIÓN: Sistema de apuestas uruguayo
-- FECHA: Noviembre 2024
-- ============================================

-- Eliminar base de datos si existe (para empezar limpio)
DROP DATABASE IF EXISTS cincodeorobd;

-- Crear base de datos
CREATE DATABASE cincodeorobd;

-- Usar la base de datos
USE cincodeorobd;

-- ============================================
-- TABLA: apuestas
-- Descripción: Almacena todas las apuestas realizadas por los jugadores
-- ============================================
CREATE TABLE apuestas (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID único de la apuesta',
    nombre_jugador VARCHAR(100) NOT NULL COMMENT 'Nombre del jugador en MAYÚSCULAS',
    fecha DATE NOT NULL COMMENT 'Fecha de la apuesta',
    num1 INT NOT NULL COMMENT 'Primer número (1-48)',
    num2 INT NOT NULL COMMENT 'Segundo número (1-48)',
    num3 INT NOT NULL COMMENT 'Tercer número (1-48)',
    num4 INT NOT NULL COMMENT 'Cuarto número (1-48)',
    num5 INT NOT NULL COMMENT 'Quinto número (1-48)',
    num6 INT DEFAULT 0 COMMENT 'Sexto número (solo apuestas múltiples)',
    num7 INT DEFAULT 0 COMMENT 'Séptimo número (solo apuestas múltiples)',
    num8 INT DEFAULT 0 COMMENT 'Octavo número (solo apuestas múltiples)',
    simple BOOLEAN NOT NULL COMMENT 'TRUE si es apuesta simple, FALSE si es múltiple',
    revancha BOOLEAN NOT NULL COMMENT 'TRUE si incluye revancha',
    costo_total DOUBLE NOT NULL COMMENT 'Costo total de la apuesta en pesos'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Registro de todas las apuestas';

-- ============================================
-- TABLA: sorteos
-- Descripción: Almacena los resultados de cada sorteo realizado
-- ============================================
CREATE TABLE sorteos (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID único del sorteo',
    fecha DATETIME NOT NULL COMMENT 'Fecha y hora del sorteo',
    num1 INT NOT NULL COMMENT 'Primer número ganador',
    num2 INT NOT NULL COMMENT 'Segundo número ganador',
    num3 INT NOT NULL COMMENT 'Tercer número ganador',
    num4 INT NOT NULL COMMENT 'Cuarto número ganador',
    num5 INT NOT NULL COMMENT 'Quinto número ganador',
    num_extra INT NOT NULL COMMENT 'Número extra (bolilla adicional)',
    total_apostado DOUBLE DEFAULT 0 COMMENT 'Total recaudado en apuestas',
    pozo_oro DOUBLE DEFAULT 0 COMMENT 'Pozo de Oro (20% del total)',
    pozo_plata DOUBLE DEFAULT 0 COMMENT 'Pozo de Plata (3.2% del total)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Historial de sorteos realizados';

-- ============================================
-- DATOS DE PRUEBA (OPCIONAL - comentar si no quieres)
-- ============================================
INSERT INTO apuestas (nombre_jugador, fecha, num1, num2, num3, num4, num5, num6, num7, num8, simple, revancha, costo_total)
VALUES 
('JUAN PEREZ', CURDATE(), 5, 12, 23, 34, 45, 0, 0, 0, TRUE, FALSE, 45),
('MARIA GOMEZ', CURDATE(), 7, 14, 21, 28, 35, 0, 0, 0, TRUE, TRUE, 65),
('PEDRO RODRIGUEZ', CURDATE(), 3, 9, 15, 27, 41, 48, 0, 0, FALSE, FALSE, 270);

-- ============================================
-- VERIFICACIÓN
-- ============================================
-- Ver estructura de tablas
DESCRIBE apuestas;
DESCRIBE sorteos;

-- Ver datos insertados
SELECT * FROM apuestas;

-- Ver total recaudado
SELECT SUM(costo_total) as total_recaudado FROM apuestas;

-- ============================================
-- CONSULTAS ÚTILES PARA DESARROLLO
-- ============================================

-- Ver todas las apuestas con formato
-- SELECT id, nombre_jugador, fecha, 
--        CONCAT(num1,',',num2,',',num3,',',num4,',',num5) as numeros,
--        IF(simple, 'Simple', 'Múltiple') as tipo,
--        IF(revancha, 'Sí', 'No') as revancha,
--        CONCAT('$', costo_total) as costo
-- FROM apuestas;

-- Ver números más jugados
-- SELECT numero, COUNT(*) as veces_jugado
-- FROM (
--     SELECT num1 as numero FROM apuestas
--     UNION ALL SELECT num2 FROM apuestas
--     UNION ALL SELECT num3 FROM apuestas
--     UNION ALL SELECT num4 FROM apuestas
--     UNION ALL SELECT num5 FROM apuestas
--     UNION ALL SELECT num6 FROM apuestas WHERE num6 > 0
--     UNION ALL SELECT num7 FROM apuestas WHERE num7 > 0
--     UNION ALL SELECT num8 FROM apuestas WHERE num8 > 0
-- ) AS todos_numeros
-- GROUP BY numero
-- ORDER BY veces_jugado DESC
-- LIMIT 10;

-- Limpiar todas las apuestas (usar con cuidado)
-- DELETE FROM apuestas;

-- Limpiar todos los sorteos (usar con cuidado)
-- DELETE FROM sorteos;
