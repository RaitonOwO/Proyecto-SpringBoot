package com.example.MiApp.service;

import com.example.MiApp.entities.Comentario;
import com.example.MiApp.entities.Publicacion;
import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.ComentarioRepository;
import com.example.MiApp.repository.PublicacionRepository;
import com.example.MiApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de comentarios en publicaciones.
 */
@Service
public class ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final PublicacionRepository publicacionRepository;

    public ComentarioService(ComentarioRepository comentarioRepository, UsuarioRepository usuarioRepository, PublicacionRepository publicacionRepository) {
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
    }

    /**
     * Crea un nuevo comentario en una publicación.
     * 
     * @param idUsuario El ID del usuario que realiza el comentario.
     * @param idPublicacion El ID de la publicación comentada.
     * @param comentario Los datos del comentario.
     * @return El comentario creado.
     */
    public Comentario crearComentario(Long idUsuario, Long idPublicacion, Comentario comentario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));

        comentario.setUsuario(usuario);
        comentario.setPublicacion(publicacion);
        return comentarioRepository.save(comentario);
    }

    /**
     * Obtiene la lista de comentarios de una publicación por su ID.
     * 
     * @param idPublicacion El ID de la publicación.
     * @return La lista de comentarios de la publicación.
     */
    public List<Comentario> obtenerComentariosPorPublicacion(Long idPublicacion) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));
        return comentarioRepository.findByPublicacion(publicacion);
    }

    /**
     * Obtiene un comentario por su ID.
     * 
     * @param id El ID del comentario.
     * @return Un Optional con el comentario si existe, de lo contrario un Optional vacío.
     */
    public Optional<Comentario> obtenerComentarioPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    /**
     * Elimina un comentario por su ID.
     * 
     * @param id El ID del comentario.
     */
    public void eliminarComentario(Long id) {
        comentarioRepository.deleteById(id);
    }
}
