package com.example.MiApp.controller;

import com.example.MiApp.entities.Seguidor;
import com.example.MiApp.service.SeguidorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguidores")
public class SeguidorController {
    private final SeguidorService seguidorService;

    public SeguidorController(SeguidorService seguidorService) {
        this.seguidorService = seguidorService;
    }

    @PostMapping
    public ResponseEntity<Seguidor> crearSeguidor(@RequestParam Long idUsuario, @RequestParam Long idSeguido, @RequestBody Seguidor seguidor) {
        Seguidor nuevoSeguidor = seguidorService.crearSeguidor(idUsuario, idSeguido, seguidor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSeguidor);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Seguidor>> obtenerSeguidoresDeUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(seguidorService.obtenerSeguidoresDeUsuario(idUsuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSeguidor(@PathVariable Long id) {
        seguidorService.eliminarSeguidor(id);
        return ResponseEntity.noContent().build();
    }
}
