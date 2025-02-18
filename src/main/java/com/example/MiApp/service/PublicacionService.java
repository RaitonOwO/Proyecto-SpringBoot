package com.example.MiApp.service;

import com.example.MiApp.entities.Publicacion;
import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.PublicacionRepository;
import com.example.MiApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de publicaciones.
 */
@Service
public class PublicacionService {
    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;

    public PublicacionService(PublicacionRepository publicacionRepository, UsuarioRepository usuarioRepository) {
        this.publicacionRepository = publicacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Crea una nueva publicación de un usuario.
     * 
     * @param idUsuario El ID del usuario que crea la publicación.
     * @param publicacion Los datos de la publicación.
     * @return La publicación creada.
     */
    public Publicacion crearPublicacion(Long idUsuario, Publicacion publicacion) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        publicacion.setUsuario(usuario);
        return publicacionRepository.save(publicacion);
    }

    /**
     * Obtiene la lista de todas las publicaciones ordenadas por fecha de creación.
     * 
     * @return La lista de todas las publicaciones.
     */
    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return publicacionRepository.findAllByOrderByFechaCreacionDesc();
    }

    /**
     * Obtiene la lista de publicaciones de un usuario por su ID.
     * 
     * @param idUsuario El ID del usuario.
     * @return La lista de publicaciones del usuario.
     */
    public List<Publicacion> obtenerPublicacionesDeUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return publicacionRepository.findByUsuario(usuario);
    }

    /**
     * Obtiene una publicación por su ID.
     * 
     * @param id El ID de la publicación.
     * @return Un Optional con la publicación si existe, de lo contrario un Optional vacío.
     */
    public Optional<Publicacion> obtenerPublicacionPorId(Long id) {
        return publicacionRepository.findById(id);
    }

    /**
     * Elimina una publicación por su ID.
     * 
     * @param id El ID de la publicación.
     */
    public void eliminarPublicacion(Long id) {
        publicacionRepository.deleteById(id);
    }
}
