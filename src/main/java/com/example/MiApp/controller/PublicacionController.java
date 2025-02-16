package com.example.MiApp.controller;

import com.example.MiApp.entities.Publicacion;
import com.example.MiApp.service.PublicacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {
    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @PostMapping
    public ResponseEntity<Publicacion> crearPublicacion(@RequestParam Long idUsuario, @RequestBody Publicacion publicacion) {
        Publicacion nuevaPublicacion = publicacionService.crearPublicacion(idUsuario, publicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPublicacion);
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
        return publicacionService.obtenerPublicacionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long id) {
        publicacionService.eliminarPublicacion(id);
        return ResponseEntity.noContent().build();
    }
}
