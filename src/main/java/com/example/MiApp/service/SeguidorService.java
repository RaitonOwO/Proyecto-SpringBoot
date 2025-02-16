package com.example.MiApp.service;

import com.example.MiApp.entities.Seguidor;
import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.SeguidorRepository;
import com.example.MiApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeguidorService {
    private final SeguidorRepository seguidorRepository;
    private final UsuarioRepository usuarioRepository;

    public SeguidorService(SeguidorRepository seguidorRepository, UsuarioRepository usuarioRepository) {
        this.seguidorRepository = seguidorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Seguidor crearSeguidor(Long idUsuario, Long idSeguido, Seguidor seguidor) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Usuario seguido = usuarioRepository.findById(idSeguido)
                .orElseThrow(() -> new RuntimeException("Usuario seguido no encontrado"));

        seguidor.setUsuario(usuario);
        seguidor.setSeguido(seguido);
        return seguidorRepository.save(seguidor);
    }

    public List<Seguidor> obtenerSeguidoresDeUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return seguidorRepository.findByUsuario(usuario);
    }

    public Optional<Seguidor> obtenerSeguidorPorId(Long id) {
        return seguidorRepository.findById(id);
    }

    public void eliminarSeguidor(Long id) {
        seguidorRepository.deleteById(id);
    }
}
