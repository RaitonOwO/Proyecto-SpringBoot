package com.example.MiApp.service;

import com.example.MiApp.entities.Seguidor;
import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.SeguidorRepository;
import com.example.MiApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de seguidores.
 */
@Service
public class SeguidorService {
    private final SeguidorRepository seguidorRepository;
    private final UsuarioRepository usuarioRepository;

    public SeguidorService(SeguidorRepository seguidorRepository, UsuarioRepository usuarioRepository) {
        this.seguidorRepository = seguidorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Crea una nueva relación de seguidor entre dos usuarios.
     * 
     * @param idUsuario El ID del usuario que sigue.
     * @param idSeguido El ID del usuario seguido.
     * @param seguidor Los datos de la relación de seguidor.
     * @return La relación de seguidor creada.
     */
    public Seguidor crearSeguidor(Long idUsuario, Long idSeguido, Seguidor seguidor) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Usuario seguido = usuarioRepository.findById(idSeguido)
                .orElseThrow(() -> new RuntimeException("Usuario seguido no encontrado"));

        seguidor.setUsuario(usuario);
        seguidor.setSeguido(seguido);
        return seguidorRepository.save(seguidor);
    }

    /**
     * Obtiene la lista de seguidores de un usuario por su ID.
     * 
     * @param idUsuario El ID del usuario.
     * @return La lista de seguidores del usuario.
     */
    public List<Seguidor> obtenerSeguidoresDeUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return seguidorRepository.findByUsuario(usuario);
    }

    /**
     * Obtiene una relación de seguidor por su ID.
     * 
     * @param id El ID de la relación de seguidor.
     * @return Un Optional con la relación de seguidor si existe, de lo contrario un Optional vacío.
     */
    public Optional<Seguidor> obtenerSeguidorPorId(Long id) {
        return seguidorRepository.findById(id);
    }

    /**
     * Elimina una relación de seguidor por su ID.
     * 
     * @param id El ID de la relación de seguidor.
     */
    public void eliminarSeguidor(Long id) {
        seguidorRepository.deleteById(id);
    }
}
