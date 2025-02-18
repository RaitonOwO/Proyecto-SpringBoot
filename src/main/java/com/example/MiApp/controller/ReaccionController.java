package com.example.MiApp.controller;

import com.example.MiApp.entities.Reaccion;
import com.example.MiApp.service.ReaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
    @Operation(summary = "Crear una nueva reacción",
               description = "Crea una nueva reacción de un usuario a una publicación",
               responses = {
                   @ApiResponse(responseCode = "201", description = "Reacción creada",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Reaccion.class))),
                   @ApiResponse(responseCode = "400", description = "Solicitud inválida")
               })
    public ResponseEntity<Reaccion> crearReaccion(
            @RequestParam @Schema(description = "ID del usuario que realiza la reacción", example = "1") Long idUsuario,
            @RequestParam @Schema(description = "ID de la publicación a la que se reacciona", example = "2") Long idPublicacion,
            @RequestBody(description = "Datos de la reacción", required = true) Reaccion reaccion) {
        Reaccion nuevaReaccion = reaccionService.crearReaccion(idUsuario, idPublicacion, reaccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReaccion);
    }

    @GetMapping("/publicacion/{idPublicacion}")
    @Operation(summary = "Obtener reacciones por publicación",
               description = "Obtiene la lista de reacciones de una publicación por su ID",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Lista de reacciones obtenida",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = List.class))),
                   @ApiResponse(responseCode = "404", description = "Publicación no encontrada")
               })
    public ResponseEntity<List<Reaccion>> obtenerReaccionesPorPublicacion(
            @PathVariable @Schema(description = "ID de la publicación", example = "1") Long idPublicacion) {
        return ResponseEntity.ok(reaccionService.obtenerReaccionesPorPublicacion(idPublicacion));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una reacción",
               description = "Elimina una reacción por su ID",
               responses = {
                   @ApiResponse(responseCode = "204", description = "Reacción eliminada"),
                   @ApiResponse(responseCode = "404", description = "Reacción no encontrada")
               })
    public ResponseEntity<Void> eliminarReaccion(
            @PathVariable @Schema(description = "ID de la reacción", example = "1") Long id) {
        reaccionService.eliminarReaccion(id);
        return ResponseEntity.noContent().build();
    }
}
