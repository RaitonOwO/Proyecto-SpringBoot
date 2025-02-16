package com.example.MiApp.service;

import com.example.MiApp.entities.Publicacion;
import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.PublicacionRepository;
import com.example.MiApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {
    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;

    public PublicacionService(PublicacionRepository publicacionRepository, UsuarioRepository usuarioRepository) {
        this.publicacionRepository = publicacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Publicacion crearPublicacion(Long idUsuario, Publicacion publicacion) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        publicacion.setUsuario(usuario);
        return publicacionRepository.save(publicacion);
    }

    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return publicacionRepository.findAllByOrderByFechaCreacionDesc();
    }

    public List<Publicacion> obtenerPublicacionesDeUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return publicacionRepository.findByUsuario(usuario);
    }

    public Optional<Publicacion> obtenerPublicacionPorId(Long id) {
        return publicacionRepository.findById(id);
    }

    public void eliminarPublicacion(Long id) {
        publicacionRepository.deleteById(id);
    }
}
