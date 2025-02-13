package com.example.MiApp.service;


import com.example.MiApp.entities.Publicacion;
import com.example.MiApp.entities.Reaccion;
import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.PublicacionRepository;
import com.example.MiApp.repository.ReaccionRepository;
import com.example.MiApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;



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

    public Reaccion agregarReaccion(Long idUsuario, Long idPublicacion, String tipo) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        Reaccion reaccion = new Reaccion();
        reaccion.setUsuario(usuario);
        reaccion.setPublicacion(publicacion);
        reaccion.setTipo(tipo);

        return reaccionRepository.save(reaccion);
    }

    public long contarReacciones(Long idPublicacion, String tipo) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        return reaccionRepository.countByPublicacionAndTipo(publicacion, tipo);
    }
}

