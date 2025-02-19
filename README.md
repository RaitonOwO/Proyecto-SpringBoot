# 🚀 Proyecto Spring Boot - Red Social

## 📝 Descripción
Este proyecto es una red social básica desarrollada con Spring Boot para el backend y HTML, CSS y JavaScript para el frontend. Se utiliza MySQL o PostgreSQL como base de datos e incluye autenticación con 🔐 JWT, gestión de usuarios 👤, publicaciones 📝, comentarios 💬, reacciones 👍, seguimiento de usuarios 👥, notificaciones 🔔 y mensajes privados 📩.

## 🛠 Tecnologías utilizadas
- **Backend**: Java ☕, Spring Boot 🌱, Spring Security 🛡, Spring Data JPA 📄
- **Base de Datos**: MySQL 🐬 / PostgreSQL 🐘
- **Frontend**: HTML 📄, CSS 🎨, JavaScript ⚡
- **Autenticación**: JWT 🔐
- **Documentación**: Swagger 📑

## 🏗 Arquitectura del Proyecto
El proyecto sigue una arquitectura en capas con los siguientes paquetes:

- **📂 Controller**: Maneja las solicitudes HTTP.
- **🛠 Service**: Contiene la lógica de negocio.
- **💾 Repository**: Interactúa con la base de datos.
- **📦 Entities**: Define las entidades de la base de datos.
- **⚙️ Config**: Configuración de seguridad y Swagger.

## 📌 Servicios y Controladores

### 🎯 Controladores

#### 👤 UsuarioController
Maneja las operaciones relacionadas con los usuarios, como:
- 📌 Registro y autenticación.
- 🔍 Obtener perfil de usuario.
- ➕ Seguir y ➖ dejar de seguir usuarios.

#### 📝 PublicacionController
Permite la gestión de publicaciones:
- ✍️ Crear, editar y eliminar publicaciones.
- 📢 Obtener publicaciones de usuarios seguidos.
- 🎭 Agregar reacciones y comentarios.

#### 💬 ComentarioController
Gestiona los comentarios en las publicaciones:
- ➕ Agregar y ❌ eliminar comentarios.
- 📜 Listar comentarios en una publicación.

#### 🔔 NotificacionController
Maneja las notificaciones del sistema:
- 🚀 Notificar reacciones, comentarios y seguimientos.
- 📥 Listar notificaciones de un usuario.

#### 👍 ReaccionController
Maneja las reacciones a las publicaciones:
- ➕ Agregar o ❌ eliminar "me gusta" y otras reacciones.

#### 👥 SeguidorController
Gestiona la relación de seguidores entre usuarios:
- ➕ Seguir y ➖ dejar de seguir a otros usuarios.
- 📋 Obtener la lista de seguidores y seguidos.

#### 📩 MensajePrivadoController
Maneja los mensajes privados entre usuarios:
- ✉️ Enviar mensajes privados.
- 📜 Listar mensajes entre usuarios.
- ❌ Eliminar mensajes enviados.

### 🛠 Servicios

#### 👤 UsuarioService
- 🏗 Gestión de usuarios y autenticación.
- 👥 Seguimiento de usuarios.

#### 📝 PublicacionService
- ✍️ Creación, edición y eliminación de publicaciones.
- 📢 Recuperación de publicaciones de usuarios seguidos.

#### 💬 ComentarioService
- ➕ Agregar y ❌ eliminar comentarios en publicaciones.
- 📜 Listar comentarios.

#### 🔔 NotificacionService
- 📩 Creación y recuperación de notificaciones.

#### 👍 ReaccionService
- ➕ Agregar o ❌ eliminar reacciones en publicaciones.

#### 👥 SeguidorService
- 🔄 Gestión de seguidores y seguidos.

#### 📩 MensajePrivadoService
- ✉️ Enviar mensajes entre usuarios.
- 📜 Listar conversaciones.
- ❌ Eliminar mensajes enviados.

#### 🔐 UserDetailsServiceImpl
- 🛡 Implementa los detalles del usuario para la autenticación con Spring Security.

### 💾 Repositorios

#### 👤 UsuarioRepository
- 💾 Acceso a la base de datos para la entidad Usuario.

#### 📝 PublicacionRepository
- 🔄 Operaciones de base de datos para la entidad Publicacion.

#### 💬 ComentarioRepository
- 📊 Acceso y manipulación de comentarios.

#### 🔔 NotificacionRepository
- 📩 Manejo de las notificaciones en la base de datos.

#### 👍 ReaccionRepository
- 🎭 Persistencia de reacciones a publicaciones.

#### 👥 SeguidorRepository
- 🔄 Gestión de relaciones entre seguidores y seguidos.

#### 📩 MensajePrivadoRepository
- ✉️ Gestión de mensajes privados entre usuarios.

### ⚙️ Configuración

#### 🛠 ApplicationConfig
- ⚙️ Configuración general de la aplicación.

#### 🔐 SecurityConfig
- 🛡 Configuración de seguridad con Spring Security y JWT.

#### 📑 SwaggerConfig
- 📝 Configuración de Swagger para documentación de la API.

## 🗂 Diagrama de la Base de Datos

![Diagrama de la base de datos](./src/main/resources/static/images/Diagrama%20ER.jpg)

## 🛠 Instalación y Ejecución

1. 📥 Clonar el repositorio:
   ```sh
   git clone https://github.com/usuario/proyecto-red-social.git
   ```

2. ⚙️ Configurar la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/red_social
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

3. 🚀 Ejecutar el proyecto:
   ```sh
   mvn spring-boot:run
   ```

4. 🌍 Acceder a la API en `http://localhost:8080/swagger-ui.html`.

## 🤝 Contribución
Si deseas contribuir, sigue estos pasos:
1. 🍴 Haz un fork del repositorio.
2. 🌿 Crea una rama con tu nueva funcionalidad (`git checkout -b nueva-funcionalidad`).
3. 📝 Realiza los cambios y haz commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. 📤 Sube los cambios (`git push origin nueva-funcionalidad`).
5. 🔃 Abre un Pull Request.

## 👨‍💻 Autor
**Joan Santos** - Desarrollador Backend y Frontend 🚀

## 📜 Licencia
Este proyecto está bajo la Licencia MIT. 📄
