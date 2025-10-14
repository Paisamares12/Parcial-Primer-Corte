-- Script para crear la base de datos y tabla de mascotas exóticas
-- Para usar con XAMPP MySQL

CREATE DATABASE IF NOT EXISTS mascotas_exoticas 
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE mascotas_exoticas;

CREATE TABLE IF NOT EXISTS Animales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    apodo VARCHAR(100) NOT NULL UNIQUE,
    alimentacion VARCHAR(50) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    clasificacion VARCHAR(50) NOT NULL,
    familia VARCHAR(100) NOT NULL,
    genero VARCHAR(100) NOT NULL,
    especie VARCHAR(100) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_apodo (apodo),
    INDEX idx_clasificacion (clasificacion),
    INDEX idx_familia (familia),
    INDEX idx_alimentacion (alimentacion)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
