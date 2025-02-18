package com.example.MiApp.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "seguidores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa la relación de seguimiento entre usuarios")
public class Seguidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la relación de seguidor", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @Schema(description = "Usuario que sigue a otro usuario", example = "1")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "seguido_id", nullable = false)
    @Schema(description = "Usuario que es seguido", example = "2")
    private Usuario seguido;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Schema(description = "Fecha de creación del registro de seguidor", example = "2023-01-01T10:00:00")
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
