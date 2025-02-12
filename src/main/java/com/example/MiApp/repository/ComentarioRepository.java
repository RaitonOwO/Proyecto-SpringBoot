package com.example.MiApp.repository;

import com.example.MiApp.entities.Comentario;
import com.example.MiApp.entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByPublicacion(Publicacion publicacion);
}
