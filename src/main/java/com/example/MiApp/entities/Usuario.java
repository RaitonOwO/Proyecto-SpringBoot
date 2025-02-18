package com.example.MiApp.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un usuario en la aplicación")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del usuario", example = "1")
    private Long id;

    @Column(name = "nombre_completo", nullable = false)
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String nombreCompleto;

    @Column(name = "nombre_usuario", unique = true, nullable = false)
    @Schema(description = "Nombre de usuario único", example = "juanperez")
    private String nombreUsuario;

    @Schema(description = "Número de celular del usuario", example = "+573001234567")
    private String celular;

    @Column(unique = true, nullable = false)
    @Schema(description = "Correo electrónico único del usuario", example = "juan.perez@example.com")
    private String correo;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Schema(description = "Fecha de nacimiento del usuario", example = "1990-01-01")
    private LocalDate fechaNacimiento;

    @Column(name = "contrasena_hash", nullable = false)
    @Schema(description = "Hash de la contraseña del usuario")
    private String contrasenaHash;

    @Schema(description = "Biografía del usuario", example = "Soy desarrollador de software...")
    private String biografia;

    @Schema(description = "URL de la foto de perfil del usuario", example = "http://example.com/foto.jpg")
    private String fotoPerfil;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Schema(description = "Fecha de creación del registro", example = "2023-01-01T10:00:00")
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
