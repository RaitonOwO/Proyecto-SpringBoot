package com.example.MiApp.controller;


import com.example.MiApp.service.ReaccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reacciones")
public class ReaccionController {

    private final ReaccionService reaccionService;

    public ReaccionController(ReaccionService reaccionService) {
        this.reaccionService = reaccionService;
    }

    @PostMapping("/{idUsuario}/{idPublicacion}/{tipo}")
    public ResponseEntity<?> agregarReaccion(@PathVariable Long idUsuario, @PathVariable Long idPublicacion, @PathVariable String tipo) {
        return ResponseEntity.ok(reaccionService.agregarReaccion(idUsuario, idPublicacion, tipo));
    }

    @GetMapping("/{idPublicacion}/{tipo}")
    public ResponseEntity<Long> contarReacciones(@PathVariable Long idPublicacion, @PathVariable String tipo) {
        return ResponseEntity.ok(reaccionService.contarReacciones(idPublicacion, tipo));
    }
}
