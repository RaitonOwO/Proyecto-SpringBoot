package com.example.MiApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "publicaciones")
@Builder
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_publicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;  

    @Column(nullable = false, length = 500)
    private String contenido;

    private String imagen;  

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha_creacion;

    public Publicacion() {
    }

    public Publicacion(Long id_publicacion, Usuario usuario, String contenido, String imagen,
            LocalDateTime fecha_creacion) {
        this.id_publicacion = id_publicacion;
        this.usuario = usuario;
        this.contenido = contenido;
        this.imagen = imagen;
        this.fecha_creacion = fecha_creacion;
    }

    public Long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Long id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
