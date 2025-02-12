package com.example.MiApp.service;

import com.example.MiApp.entities.Comentario;
import com.example.MiApp.entities.Publicacion;
import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.ComentarioRepository;
import com.example.MiApp.repository.PublicacionRepository;
import com.example.MiApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Comentario agregarComentario(Long idUsuario, Long idPublicacion, Comentario comentario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        comentario.setUsuario(usuario);
        comentario.setPublicacion(publicacion);
        return comentarioRepository.save(comentario);
    }

    public List<Comentario> obtenerComentariosDePublicacion(Long idPublicacion) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        return comentarioRepository.findByPublicacion(publicacion);
    }
}

