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

/**
 * Servicio para la gestión de reacciones a publicaciones.
 */
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

    /**
     * Crea una nueva reacción de un usuario a una publicación.
     * 
     * @param idUsuario El ID del usuario que realiza la reacción.
     * @param idPublicacion El ID de la publicación a la que se reacciona.
     * @param reaccion Los datos de la reacción.
     * @return La reacción creada.
     */
    public Reaccion crearReaccion(Long idUsuario, Long idPublicacion, Reaccion reaccion) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));

        reaccion.setUsuario(usuario);
        reaccion.setPublicacion(publicacion);
        return reaccionRepository.save(reaccion);
    }

    /**
     * Obtiene la lista de reacciones a una publicación por su ID.
     * 
     * @param idPublicacion El ID de la publicación.
     * @return La lista de reacciones a la publicación.
     */
    public List<Reaccion> obtenerReaccionesPorPublicacion(Long idPublicacion) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));
        return reaccionRepository.findByPublicacion(publicacion);
    }

    /**
     * Obtiene una reacción por su ID.
     * 
     * @param id El ID de la reacción.
     * @return Un Optional con la reacción si existe, de lo contrario un Optional vacío.
     */
    public Optional<Reaccion> obtenerReaccionPorId(Long id) {
        return reaccionRepository.findById(id);
    }

    /**
     * Elimina una reacción por su ID.
     * 
     * @param id El ID de la reacción.
     */
    public void eliminarReaccion(Long id) {
        reaccionRepository.deleteById(id);
    }
}
