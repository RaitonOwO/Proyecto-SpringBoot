package com.example.MiApp.controller;

import com.example.MiApp.entities.Publicacion;
import com.example.MiApp.service.PublicacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }


    @PostMapping("/{idUsuario}")
    public ResponseEntity<Publicacion> crearPublicacion(@PathVariable Long idUsuario, @RequestBody Publicacion publicacion) {
        Publicacion nuevaPublicacion = publicacionService.crearPublicacion(idUsuario, publicacion);
        return ResponseEntity.ok(nuevaPublicacion);
    }

    @GetMapping
    public ResponseEntity<List<Publicacion>> obtenerTodasLasPublicaciones() {
        return ResponseEntity.ok(publicacionService.obtenerTodasLasPublicaciones());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Publicacion>> obtenerPublicacionesDeUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionesDeUsuario(idUsuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPublicacionPorId(@PathVariable Long id) {
        Optional<Publicacion> publicacion = publicacionService.obtenerPublicacionPorId(id);
        return publicacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long id) {
        publicacionService.eliminarPublicacion(id);
        return ResponseEntity.noContent().build();
    }
}

