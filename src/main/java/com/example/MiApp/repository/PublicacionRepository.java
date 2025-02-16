package com.example.MiApp.repository;

import com.example.MiApp.entities.Publicacion;
import com.example.MiApp.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    List<Publicacion> findByUsuario(Usuario usuario);
    List<Publicacion> findAllByOrderByFechaCreacionDesc();
}
