package com.example.MiApp.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "publicaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa una publicación en la aplicación")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la publicación", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @Schema(description = "Usuario que creó la publicación", example = "1")
    private Usuario usuario;

    @Column(nullable = false)
    @Schema(description = "Texto de la publicación", example = "Este es el texto de la publicación...")
    private String texto;

    @Schema(description = "URL de la imagen asociada a la publicación", example = "http://example.com/imagen.jpg")
    private String imagenUrl;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Schema(description = "Fecha de creación de la publicación", example = "2023-01-01T10:00:00")
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
