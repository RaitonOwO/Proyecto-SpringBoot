package com.example.MiApp.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa una notificación en la aplicación")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la notificación", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @Schema(description = "Usuario que recibe la notificación", example = "1")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Tipo de notificación", example = "COMENTARIO")
    private TipoNotificacion tipo;

    @Column(name = "referencia_id", nullable = false)
    @Schema(description = "ID de la entidad referenciada por la notificación", example = "123")
    private Long referenciaId;

    @Schema(description = "Indica si la notificación ha sido vista", example = "false")
    private Boolean visto;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Schema(description = "Fecha de creación de la notificación", example = "2023-01-01T10:00:00")
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.visto = false;
    }

    public enum TipoNotificacion {
        @Schema(description = "Comentario en una publicación")
        COMENTARIO,

        @Schema(description = "Me gusta en una publicación")
        ME_GUSTA,

        @Schema(description = "Mención en una publicación")
        MENCION,

        @Schema(description = "Nuevo seguidor")
        SEGUIMIENTO
    }
}
