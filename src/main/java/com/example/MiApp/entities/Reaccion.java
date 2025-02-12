package com.example.MiApp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reacciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;

    @Column(nullable = false)
    private String tipo; // Ejemplo: "LIKE", "LOVE", "ANGRY", etc.
}
