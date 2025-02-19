-- Active: 1739902298079@@127.0.0.1@3306@mi_red_social


-- 1️⃣ Eliminar la base de datos si ya existe
DROP DATABASE IF EXISTS mi_red_social;
CREATE DATABASE mi_red_social;
USE mi_red_social;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    celular VARCHAR(15),
    correo VARCHAR(100) UNIQUE NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    contrasena_hash TEXT NOT NULL,
    biografia TEXT,
    foto_perfil TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS publicaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    texto TEXT NOT NULL,
    imagen_url TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_texto_length CHECK (CHAR_LENGTH(texto) BETWEEN 5 AND 500),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comentarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    publicacion_id INT NOT NULL,
    usuario_id INT NOT NULL,
    texto VARCHAR(200) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reacciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    publicacion_id INT NOT NULL,
    usuario_id INT NOT NULL,
    tipo ENUM('me_gusta') NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (publicacion_id, usuario_id),
    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS seguidores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    seguido_id INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (usuario_id, seguido_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (seguido_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS notificaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    tipo ENUM('comentario', 'me_gusta', 'mencion', 'seguimiento') NOT NULL,
    referencia_id INT NOT NULL,
    visto BOOLEAN DEFAULT FALSE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);



-- 8️⃣ Inserts de Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    celular VARCHAR(15),
    correo VARCHAR(100) UNIQUE NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    contrasena_hash TEXT NOT NULL,
    biografia TEXT,
    foto_perfil TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS publicaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    texto TEXT NOT NULL,
    imagen_url TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_texto_length CHECK (CHAR_LENGTH(texto) BETWEEN 5 AND 500),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comentarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    publicacion_id INT NOT NULL,
    usuario_id INT NOT NULL,
    texto VARCHAR(200) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reacciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    publicacion_id INT NOT NULL,
    usuario_id INT NOT NULL,
    tipo ENUM('me_gusta') NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (publicacion_id, usuario_id),
    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS seguidores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    seguido_id INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (usuario_id, seguido_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (seguido_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS notificaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    tipo ENUM('comentario', 'me_gusta', 'mencion', 'seguimiento') NOT NULL,
    referencia_id INT NOT NULL,
    visto BOOLEAN DEFAULT FALSE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Inserciones de ejemplo
INSERT INTO usuarios (nombre_completo, nombre_usuario, celular, correo, fecha_nacimiento, contrasena_hash, biografia, foto_perfil) VALUES
('Juan Pérez', 'juanp', '1234567890', 'juanp@example.com', '2000-05-20', 'hashedpassword1', 'Desarrollador apasionado.', 'https://example.com/juan.jpg'),
('María López', 'marial', '0987654321', 'marial@example.com', '1998-09-15', 'hashedpassword2', 'Amante de la tecnología.', 'https://example.com/maria.jpg'),
('Carlos Ramírez', 'carlitos', '3216549870', 'carlosr@example.com', '1995-07-10', 'hashedpassword3', 'Ingeniero de software.', 'https://example.com/carlos.jpg'),
('Laura Méndez', 'lauram', '9876543210', 'lauram@example.com', '2001-02-25', 'hashedpassword4', 'Me encanta el diseño web.', 'https://example.com/laura.jpg');

INSERT INTO publicaciones (usuario_id, texto, imagen_url, fecha_creacion) VALUES
((SELECT id FROM usuarios WHERE nombre_usuario = 'juanp'), 'Mi primera publicación en esta red social!', 'https://example.com/image1.jpg', '2025-02-15 10:00:00'),
((SELECT id FROM usuarios WHERE nombre_usuario = 'marial'), 'Hoy es un gran día para programar!', NULL, '2025-02-15 10:30:00'),
((SELECT id FROM usuarios WHERE nombre_usuario = 'carlitos'), 'Trabajando en un nuevo proyecto!', 'https://example.com/proyecto.jpg', '2025-02-15 11:00:00'),
((SELECT id FROM usuarios WHERE nombre_usuario = 'lauram'), 'Explorando nuevas tecnologías web!', NULL, '2025-02-15 11:30:00');

INSERT INTO comentarios (publicacion_id, usuario_id, texto, fecha_creacion) VALUES
((SELECT id FROM publicaciones WHERE texto = 'Mi primera publicación en esta red social!'), 
 (SELECT id FROM usuarios WHERE nombre_usuario = 'marial'), '¡Genial publicación, Juan!', '2025-02-15 10:05:00'),
((SELECT id FROM publicaciones WHERE texto = 'Hoy es un gran día para programar!'), 
 (SELECT id FROM usuarios WHERE nombre_usuario = 'juanp'), 'Totalmente de acuerdo, María.', '2025-02-15 10:35:00'),
((SELECT id FROM publicaciones WHERE texto = 'Trabajando en un nuevo proyecto!'), 
 (SELECT id FROM usuarios WHERE nombre_usuario = 'lauram'), '¡Suena interesante!', '2025-02-15 11:15:00');

INSERT INTO reacciones (publicacion_id, usuario_id, tipo, fecha_creacion) VALUES
((SELECT id FROM publicaciones WHERE texto = 'Mi primera publicación en esta red social!'), 
 (SELECT id FROM usuarios WHERE nombre_usuario = 'marial'), 'me_gusta', '2025-02-15 10:06:00'),
((SELECT id FROM publicaciones WHERE texto = 'Hoy es un gran día para programar!'), 
 (SELECT id FROM usuarios WHERE nombre_usuario = 'juanp'), 'me_gusta', '2025-02-15 10:36:00'),
((SELECT id FROM publicaciones WHERE texto = 'Trabajando en un nuevo proyecto!'), 
 (SELECT id FROM usuarios WHERE nombre_usuario = 'lauram'), 'me_gusta', '2025-02-15 11:20:00');

INSERT INTO seguidores (usuario_id, seguido_id, fecha_creacion) VALUES
((SELECT id FROM usuarios WHERE nombre_usuario = 'juanp'), 
 (SELECT id FROM usuarios WHERE nombre_usuario = 'marial'), '2025-02-15 10:10:00'),
((SELECT id FROM usuarios WHERE nombre_usuario = 'marial'), 
 (SELECT id FROM usuarios WHERE nombre_usuario = 'juanp'), '2025-02-15 10:15:00'),
((SELECT id FROM usuarios WHERE nombre_usuario = 'carlitos'), 
 (SELECT id FROM usuarios WHERE nombre_usuario = 'lauram'), '2025-02-15 11:25:00');

INSERT INTO notificaciones (usuario_id, tipo, referencia_id, visto, fecha_creacion) VALUES
((SELECT id FROM usuarios WHERE nombre_usuario = 'juanp'), 'comentario', 
 (SELECT id FROM comentarios WHERE texto = '¡Genial publicación, Juan!'), FALSE, '2025-02-15 10:07:00'),
((SELECT id FROM usuarios WHERE nombre_usuario = 'marial'), 'me_gusta', 
 (SELECT id FROM publicaciones WHERE texto = 'Hoy es un gran día para programar!'), FALSE, '2025-02-15 10:37:00'),
((SELECT id FROM usuarios WHERE nombre_usuario = 'carlitos'), 'comentario', 
 (SELECT id FROM comentarios WHERE texto = '¡Suena interesante!'), FALSE, '2025-02-15 11:30:00');
    