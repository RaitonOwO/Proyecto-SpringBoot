
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
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        publicacion.setUsuario(usuario);
        return publicacionRepository.save(publicacion);
    }

    public List<Publicacion> obtenerTodasLasPublicaciones() {
        try {
            return publicacionRepository.findAllByOrderByFechaCreacionDesc();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener publicaciones", e);
        }
    }

    public Optional<Publicacion> obtenerPublicacionPorId(Long id) {
        return publicacionRepository.findById(id);
    }

    public void eliminarPublicacion(Long id) {
        if (!publicacionRepository.existsById(id)) {
            throw new IllegalArgumentException("Publicación no encontrada");
        }
        publicacionRepository.deleteById(id);
    }
}
      