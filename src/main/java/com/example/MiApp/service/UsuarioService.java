package com.example.MiApp.service;

import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id_usuario) {
        return usuarioRepository.findById(id_usuario);
    }

    public Usuario actualizarUsuario(Long id_usuario, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id_usuario).map(usuario -> {
            usuario.setNombre_completo(usuarioActualizado.getNombre_completo());
            usuario.setNombre_usuario(usuarioActualizado.getNombre_usuario());
            usuario.setCorreo(usuarioActualizado.getCorreo());
            usuario.setContrasena(usuarioActualizado.getContrasena());
            usuario.setBiografia(usuarioActualizado.getBiografia());
            usuario.setFotoPerfil(usuarioActualizado.getFotoPerfil());
            return usuarioRepository.save(usuario);
        }).orElse(null);
    }

    public boolean eliminarUsuario(Long id_usuario) {
        if (usuarioRepository.existsById(id_usuario)) {
            usuarioRepository.deleteById(id_usuario);
            return true;
        }
        return false;
    }
}

