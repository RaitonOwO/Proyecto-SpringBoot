package com.example.MiApp.controller;

import com.example.MiApp.entities.Comentario;
import com.example.MiApp.service.ComentarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping("/{idUsuario}/{idPublicacion}")
    public ResponseEntity<Comentario> agregarComentario(@PathVariable Long idUsuario, @PathVariable Long idPublicacion, @RequestBody Comentario comentario) {
        return ResponseEntity.ok(comentarioService.agregarComentario(idUsuario, idPublicacion, comentario));
    }

    @GetMapping("/{idPublicacion}")
    public ResponseEntity<List<Comentario>> obtenerComentarios(@PathVariable Long idPublicacion) {
        return ResponseEntity.ok(comentarioService.obtenerComentariosDePublicacion(idPublicacion));
    }
}
