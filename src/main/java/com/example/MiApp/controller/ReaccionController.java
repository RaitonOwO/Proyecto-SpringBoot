package com.example.MiApp.controller;

import com.example.MiApp.entities.Reaccion;
import com.example.MiApp.service.ReaccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reacciones")
public class ReaccionController {
    private final ReaccionService reaccionService;

    public ReaccionController(ReaccionService reaccionService) {
        this.reaccionService = reaccionService;
    }

    @PostMapping
    public ResponseEntity<Reaccion> crearReaccion(@RequestParam Long idUsuario, @RequestParam Long idPublicacion, @RequestBody Reaccion reaccion) {
        Reaccion nuevaReaccion = reaccionService.crearReaccion(idUsuario, idPublicacion, reaccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReaccion);
    }

    @GetMapping("/publicacion/{idPublicacion}")
    public ResponseEntity<List<Reaccion>> obtenerReaccionesPorPublicacion(@PathVariable Long idPublicacion) {
        return ResponseEntity.ok(reaccionService.obtenerReaccionesPorPublicacion(idPublicacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReaccion(@PathVariable Long id) {
        reaccionService.eliminarReaccion(id);
        return ResponseEntity.noContent().build();
    }
}
