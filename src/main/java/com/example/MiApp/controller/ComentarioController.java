package com.example.MiApp.controller;

import com.example.MiApp.entities.Comentario;
import com.example.MiApp.service.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {
    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping
    public ResponseEntity<Comentario> crearComentario(@RequestParam Long idUsuario, @RequestParam Long idPublicacion, @RequestBody Comentario comentario) {
        Comentario nuevoComentario = comentarioService.crearComentario(idUsuario, idPublicacion, comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }

    @GetMapping("/publicacion/{idPublicacion}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorPublicacion(@PathVariable Long idPublicacion) {
        return ResponseEntity.ok(comentarioService.obtenerComentariosPorPublicacion(idPublicacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
