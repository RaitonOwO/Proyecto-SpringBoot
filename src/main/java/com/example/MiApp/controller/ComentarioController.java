package com.example.MiApp.controller;

import com.example.MiApp.entities.Comentario;
import com.example.MiApp.service.ComentarioService;
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
@RequestMapping("/api/comentarios")
public class ComentarioController {
    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo comentario",
               description = "Crea un nuevo comentario en una publicación",
               responses = {
                   @ApiResponse(responseCode = "201", description = "Comentario creado",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Comentario.class))),
                   @ApiResponse(responseCode = "400", description = "Solicitud inválida")
               })
    public ResponseEntity<Comentario> crearComentario(
            @RequestParam @Schema(description = "ID del usuario que realiza el comentario", example = "1") Long idUsuario,
            @RequestParam @Schema(description = "ID de la publicación comentada", example = "2") Long idPublicacion,
            @RequestBody(description = "Datos del nuevo comentario", required = true) Comentario comentario) {
        Comentario nuevoComentario = comentarioService.crearComentario(idUsuario, idPublicacion, comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }

    @GetMapping("/publicacion/{idPublicacion}")
    @Operation(summary = "Obtener comentarios por publicación",
               description = "Obtiene una lista de comentarios de una publicación por su ID",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Lista de comentarios obtenida",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = List.class))),
                   @ApiResponse(responseCode = "404", description = "Publicación no encontrada")
               })
    public ResponseEntity<List<Comentario>> obtenerComentariosPorPublicacion(
            @PathVariable @Schema(description = "ID de la publicación", example = "1") Long idPublicacion) {
        return ResponseEntity.ok(comentarioService.obtenerComentariosPorPublicacion(idPublicacion));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un comentario",
               description = "Elimina un comentario por su ID",
               responses = {
                   @ApiResponse(responseCode = "204", description = "Comentario eliminado"),
                   @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
               })
    public ResponseEntity<Void> eliminarComentario(
            @PathVariable @Schema(description = "ID del comentario", example = "1") Long id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
