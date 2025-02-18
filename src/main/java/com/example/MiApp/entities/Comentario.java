package com.example.MiApp.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un comentario en una publicación")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del comentario", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    @Schema(description = "Publicación a la que pertenece el comentario", example = "1")
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @Schema(description = "Usuario que realizó el comentario", example = "1")
    private Usuario usuario;

    @Column(nullable = false)
    @Schema(description = "Texto del comentario", example = "Este es el texto del comentario...")
    private String texto;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Schema(description = "Fecha de creación del comentario", example = "2023-01-01T10:00:00")
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
}
