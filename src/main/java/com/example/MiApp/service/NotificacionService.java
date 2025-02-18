package com.example.MiApp.service;

import com.example.MiApp.entities.Notificacion;
import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.NotificacionRepository;
import com.example.MiApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de notificaciones.
 */
@Service
public class NotificacionService {
    private final NotificacionRepository notificacionRepository;
    private final UsuarioRepository usuarioRepository;

    public NotificacionService(NotificacionRepository notificacionRepository, UsuarioRepository usuarioRepository) {
        this.notificacionRepository = notificacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Crea una nueva notificación para un usuario.
     * 
     * @param idUsuario El ID del usuario que recibe la notificación.
     * @param notificacion Los datos de la notificación.
     * @return La notificación creada.
     */
    public Notificacion crearNotificacion(Long idUsuario, Notificacion notificacion) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        notificacion.setUsuario(usuario);
        return notificacionRepository.save(notificacion);
    }

    /**
     * Obtiene la lista de notificaciones de un usuario por su ID.
     * 
     * @param idUsuario El ID del usuario.
     * @return La lista de notificaciones del usuario.
     */
    public List<Notificacion> obtenerNotificacionesDeUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return notificacionRepository.findByUsuario(usuario);
    }

    /**
     * Obtiene una notificación por su ID.
     * 
     * @param id El ID de la notificación.
     * @return Un Optional con la notificación si existe, de lo contrario un Optional vacío.
     */
    public Optional<Notificacion> obtenerNotificacionPorId(Long id) {
        return notificacionRepository.findById(id);
    }

    /**
     * Elimina una notificación por su ID.
     * 
     * @param id El ID de la notificación.
     */
    public void eliminarNotificacion(Long id) {
        notificacionRepository.deleteById(id);
    }
}
