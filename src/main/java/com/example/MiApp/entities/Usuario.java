package com.example.MiApp.entities;

import jakarta.persistence.*;
import lombok0l, .*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")

public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    
    private String nombre_completo;
    
    @Column(unique = true, nullable = false)
    private String nombre_usuario;
    
    private String correo;
    private String contrasena;
    private String biografia;
    private String fotoPerfil;
    
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha_creacion;

    public Usuario() {
    }

    public Usuario(Long id_usuario, String nombre_completo, String nombre_usuario, String correo, String contrasena,
            String biografia, String fotoPerfil, LocalDateTime fecha_creacion) {
        this.id_usuario = id_usuario;
        this.nombre_completo = nombre_completo;
        this.nombre_usuario = nombre_usuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.biografia = biografia;
        this.fotoPerfil = fotoPerfil;
        this.fecha_creacion = fecha_creacion;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    
}