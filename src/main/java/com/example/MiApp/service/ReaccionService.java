package com.example.MiApp.service;

import com.example.MiApp.entities.Reaccion;
import com.example.MiApp.entities.Publicacion;
import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.ReaccionRepository;
import com.example.MiApp.repository.PublicacionRepository;
import com.example.MiApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaccionService {
    private final ReaccionRepository reaccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final PublicacionRepository publicacionRepository;

    public ReaccionService(ReaccionRepository reaccionRepository, UsuarioRepository usuarioRepository, PublicacionRepository publicacionRepository) {
        this.reaccionRepository = reaccionRepository;
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
    }

    public Reaccion crearReaccion(Long idUsuario, Long idPublicacion, Reaccion reaccion) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));

        reaccion.setUsuario(usuario);
        reaccion.setPublicacion(publicacion);
        return reaccionRepository.save(reaccion);
    }

    public List<Reaccion> obtenerReaccionesPorPublicacion(Long idPublicacion) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));
        return reaccionRepository.findByPublicacion(publicacion);
    }

    public Optional<Reaccion> obtenerReaccionPorId(Long id) {
        return reaccionRepository.findById(id);
    }

    public void eliminarReaccion(Long id) {
        reaccionRepository.deleteById(id);
    }
}
