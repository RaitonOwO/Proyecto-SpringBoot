package com.example.MiApp.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reacciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa una reacción a una publicación")
public class Reaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la reacción", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    @Schema(description = "Publicación a la que se reaccionó", example = "1")
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @Schema(description = "Usuario que realizó la reacción", example = "1")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Tipo de reacción", example = "ME_GUSTA")
    private TipoReaccion tipo;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Schema(description = "Fecha de creación de la reacción", example = "2023-01-01T10:00:00")
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public enum TipoReaccion {
        @Schema(description = "Me gusta")
        ME_GUSTA
    }
}
