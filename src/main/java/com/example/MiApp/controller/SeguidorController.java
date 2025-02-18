package com.example.MiApp.controller;

import com.example.MiApp.entities.Seguidor;
import com.example.MiApp.service.SeguidorService;
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
@RequestMapping("/api/seguidores")
public class SeguidorController {
    private final SeguidorService seguidorService;

    public SeguidorController(SeguidorService seguidorService) {
        this.seguidorService = seguidorService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva relación de seguidor",
               description = "Crea una nueva relación de seguidor entre dos usuarios",
               responses = {
                   @ApiResponse(responseCode = "201", description = "Relación de seguidor creada",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Seguidor.class))),
                   @ApiResponse(responseCode = "400", description = "Solicitud inválida")
               })
    public ResponseEntity<Seguidor> crearSeguidor(
            @RequestParam @Schema(description = "ID del usuario que sigue", example = "1") Long idUsuario,
            @RequestParam @Schema(description = "ID del usuario seguido", example = "2") Long idSeguido,
            @RequestBody(description = "Datos de la relación de seguidor", required = true) Seguidor seguidor) {
        Seguidor nuevoSeguidor = seguidorService.crearSeguidor(idUsuario, idSeguido, seguidor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSeguidor);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener seguidores de un usuario",
               description = "Obtiene la lista de seguidores de un usuario por su ID",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Lista de seguidores obtenida",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = List.class))),
                   @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
               })
    public ResponseEntity<List<Seguidor>> obtenerSeguidoresDeUsuario(
            @PathVariable @Schema(description = "ID del usuario", example = "1") Long idUsuario) {
        return ResponseEntity.ok(seguidorService.obtenerSeguidoresDeUsuario(idUsuario));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una relación de seguidor",
               description = "Elimina una relación de seguidor por su ID",
               responses = {
                   @ApiResponse(responseCode = "204", description = "Relación de seguidor eliminada"),
                   @ApiResponse(responseCode = "404", description = "Relación de seguidor no encontrada")
               })
    public ResponseEntity<Void> eliminarSeguidor(
            @PathVariable @Schema(description = "ID de la relación de seguidor", example = "1") Long id) {
        seguidorService.eliminarSeguidor(id);
        return ResponseEntity.noContent().build();
    }
}
