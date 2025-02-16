package com.example.MiApp.repository;

import com.example.MiApp.entities.Reaccion;
import com.example.MiApp.entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {
    List<Reaccion> findByPublicacion(Publicacion publicacion);
}
