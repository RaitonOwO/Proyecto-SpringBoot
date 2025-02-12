package com.example.MiApp.repository;

import com.example.MiApp.entities.Reaccion;
import com.example.MiApp.entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {
    List<Reaccion> findByPublicacion(Publicacion publicacion);
    long countByPublicacionAndTipo(Publicacion publicacion, String tipo);
}
