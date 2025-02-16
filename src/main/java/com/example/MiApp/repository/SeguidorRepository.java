package com.example.MiApp.repository;

import com.example.MiApp.entities.Seguidor;
import com.example.MiApp.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguidorRepository extends JpaRepository<Seguidor, Long> {
    List<Seguidor> findByUsuario(Usuario usuario);
    List<Seguidor> findBySeguido(Usuario seguido);
}
