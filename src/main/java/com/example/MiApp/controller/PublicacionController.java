package com.example.MiApp.controller;

import com.example.MiApp.entities.Publicacion;
import com.example.MiApp.service.PublicacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin(origins = "http://localhost:5173")
public class PublicacionController {
    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva publicación",
               description = "Crea una nueva publicación de un usuario",
               responses = {
                   @ApiResponse(responseCode = "201", description = "Publicación creada",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Publicacion.class))),
                   @ApiResponse(responseCode = "400", description = "Solicitud inválida")
               })
    public ResponseEntity<Publicacion> crearPublicacion(
            @RequestBody @Schema(description = "Datos de la nueva publicación", required = true) Publicacion publicacion) {
        
        if (publicacion.getUsuario() == null || publicacion.getUsuario().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Publicacion nuevaPublicacion = publicacionService.crearPublicacion(publicacion.getUsuario().getId(), publicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPublicacion);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las publicaciones",
               description = "Obtiene una lista de todas las publicaciones",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Lista de publicaciones obtenida",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = List.class)))
               })
    public ResponseEntity<List<Publicacion>> obtenerTodasLasPublicaciones() {
        List<Publicacion> publicaciones = publicacionService.obtenerTodasLasPublicaciones();
        return ResponseEntity.ok(publicaciones);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener publicación por ID",
               description = "Obtiene los detalles de una publicación por su ID",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Publicación encontrada",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Publicacion.class))),
                   @ApiResponse(responseCode = "404", description = "Publicación no encontrada")
               })
    public ResponseEntity<Publicacion> obtenerPublicacionPorId(
            @PathVariable @Schema(description = "ID de la publicación", example = "1") Long id) {
        return publicacionService.obtenerPublicacionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una publicación",
               description = "Elimina una publicación por su ID",
               responses = {
                   @ApiResponse(responseCode = "204", description = "Publicación eliminada"),
                   @ApiResponse(responseCode = "404", description = "Publicación no encontrada")
               })
    public ResponseEntity<Void> eliminarPublicacion(
            @PathVariable @Schema(description = "ID de la publicación", example = "1") Long id) {
        publicacionService.eliminarPublicacion(id);
        return ResponseEntity.noContent().build();
    }
}