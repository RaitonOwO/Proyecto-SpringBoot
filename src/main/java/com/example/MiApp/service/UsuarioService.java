package com.example.MiApp.service;

import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio para la gestión de usuarios.
 */
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Crea un nuevo usuario.
     * 
     * @param usuario Los datos del nuevo usuario.
     * @return El usuario creado.
     */
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param id El ID del usuario.
     * @return Un Optional con el usuario si existe, de lo contrario un Optional vacío.
     */
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Obtiene un usuario por su nombre de usuario.
     * 
     * @param nombreUsuario El nombre de usuario.
     * @return Un Optional con el usuario si existe, de lo contrario un Optional vacío.
     */
    public Optional<Usuario> obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    /**
     * Elimina un usuario por su ID.
     * 
     * @param id El ID del usuario.
     */
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
